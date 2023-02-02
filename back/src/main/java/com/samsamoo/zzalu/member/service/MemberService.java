package com.samsamoo.zzalu.member.service;

import com.samsamoo.zzalu.auth.dto.TokenInfo;
import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.mail.service.MailService;
import com.samsamoo.zzalu.member.dto.*;
import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.member.exception.*;
import com.samsamoo.zzalu.member.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailService mailService;


    @Value("${jwt.token.secret}")
    private String secretKey;

    @Transactional
    public MemberDTO signup(SignupRequest signupRequest) {
        // 아이디 중복
        if (!checkUniqueUsername(signupRequest.getUsername())) {
            throw new NotMatchException("이미 존재하는 아이디입니다.");
        }
        // 닉네임 중복
        if (!checkUniqueNickname(signupRequest.getNickname())) {
            throw new NotMatchException("이미 존재하는 닉네임입니다.");
        }
        validatePassword(signupRequest.getPassword(),signupRequest.getPasswordConfirmation()); // 비번, 비번확인 일치 여부
        mailService.checkUniqueUserEmail(signupRequest.getUserEmail()); // 이메일 중복 > 바로 예외 발생

        // 비밀번호 인코딩
        String rawPassword = signupRequest.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        signupRequest.setPassword(encPassword);
        Member savedMember = memberRepository.save(signupRequest.toEntity());

        return new MemberDTO(savedMember);
    }

    // 수정
    private void validatePassword(String password, String passwordConfirmation) {
        if (!password.equals(passwordConfirmation)) {
            throw new PasswordConfirmationException();
        }
    }
    public Boolean checkUniqueUsername(String username) {
        return !memberRepository.existsMemberByUsername(username);

    }

    public Boolean checkUniqueNickname(String nickname) {
        return !memberRepository.existsMemberByNickname(nickname);

    }

    // 토큰 검증 실패 시 에외 발생
    public void checkToken(String token) {
        if (!jwtTokenProvider.validateToken(token)) {
            throw new InvalidTokenException();
        }
    }


    public TokenInfo login(String username, String password) {
        // username 없을 때 리턴
        Member selectedMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberNotFoundException());

        checkPasswordMatch(selectedMember, password);

        // exception 안 났으면 토큰 발행
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        log.info("{} 출력", authenticationToken.getCredentials().toString()); // 출력확인  123456ddhd!

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        log.info("{} 출력", authentication);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;

    }

    private void checkPasswordMatch(Member member, String password) {
        // password 틀림
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 다릅니다.");
        }
    }

    public ProfileDTO getMyProfile(String token) {
        // 토큰 검증
        checkToken(token);

        Member me = jwtTokenProvider.getMember(token);
        ProfileDTO myProfile = new ProfileDTO(me);
        return myProfile;
    }


    public ProfileDTO getProfile(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException());
        ProfileDTO profile = new ProfileDTO(member);
        return profile;
    }

    public void updateMember(String token, UpdateMemberRequest request) {
        // 토큰 인증
        checkToken(token);
        // 닉네임 중복 체크
        if (!checkUniqueNickname(request.getNickname())) {
            throw new NotMatchException("닉네임이 중복입니다.");
        }
        // 토큰에서 Member 반환
        Member me = jwtTokenProvider.getMember(token);
        // Member 수정
        me.update(request);
        // Member 저장
        memberRepository.save(me);
    }

    @Transactional
    public void deleteMember(String token, String rawPassword) {
        checkToken(token);
        Member me = jwtTokenProvider.getMember(token);

        checkPasswordMatch(me, rawPassword);

        // 나의 팔로잉 리스트의 유저(you)들의 follower에서 나를 삭제
        List<Member> followings = me.getFollowing();
        Iterator<Member> followingItr = followings.iterator();
        while (followingItr.hasNext()) {
            Member you = followingItr.next();
            // 나를 삭제
            you.getFollower().remove(me);
            memberRepository.save(you);
        }

        // 나의 팔로우 리스트의 유저들의 following에서 나를 삭제
        List<Member> followers = me.getFollower();
        Iterator<Member> followerItr = followers.iterator();
        while (followerItr.hasNext()) {
            Member you = followerItr.next();
            // 나를 삭제
            you.getFollowing().remove(me);
            memberRepository.save(you);
        }
        memberRepository.delete(me);
    }

    public void changePass(ChangePassRequest request) {
        // member 반환
        Member member = memberRepository.findById(request.getId())
                .orElseThrow(() -> new MemberNotFoundException());

        // 비밀번호 확인과 일치하는지
        String newPass = request.getNewPassword();
        validatePassword(newPass, request.getNewPasswordConfirmation());

        // 비밀번호 형식이 맞는지 확인
        Pattern passPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$");
        Matcher passMatcher = passPattern.matcher(newPass);
        if(!passMatcher.find()){
            throw new InvalidPasswordException("비밀번호는 영문과 특수문자 숫자를 포함하며 8~20자이어야 합니다.");
        }

        // member의 비밀번호 변경
        member.changePass(passwordEncoder.encode(newPass));

        // member 저장
        memberRepository.save(member);
    }
}
