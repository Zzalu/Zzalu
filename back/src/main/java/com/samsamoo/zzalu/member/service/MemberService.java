package com.samsamoo.zzalu.member.service;

import com.samsamoo.zzalu.auth.dto.TokenInfo;
import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.member.dto.*;
import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.member.exception.AuthorizationException;
import com.samsamoo.zzalu.member.exception.InvalidPasswordException;
import com.samsamoo.zzalu.member.exception.MemberNotFoundException;
import com.samsamoo.zzalu.member.exception.PasswordConfirmationException;
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
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;


    @Value("${jwt.token.secret}")
    private String secretKey;

    @Transactional
    public MemberDTO signup(SignupRequest signupRequest) {
        validatePassword(signupRequest.getPassword(),signupRequest.getPasswordConfirmation());
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
    public UniqueResponse checkUniqueUsername(String username) {
        boolean unique = !memberRepository.existsMemberByUsername(username);
        return new UniqueResponse(unique);
    }

    public UniqueResponse checkUniqueNickname(String nickname) {
        boolean unique = !memberRepository.existsMemberByNickname(nickname);
        return new UniqueResponse(unique);
    }


    public TokenInfo login(String username, String password) {
        // username 없을 때 리턴
        Member selectedMember = memberRepository.findByUsername(username)
                .orElseThrow(() -> new MemberNotFoundException());
        // password 틀림
        if (!passwordEncoder.matches(password, selectedMember.getPassword())) {
            throw new InvalidPasswordException();
        }
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
    public MemberDTO getMyProfile(String token, String username) {

        Member member = jwtTokenProvider.getMember(token);
        log.info("여기까지 OK");

        return new MemberDTO(member);

    }

    public FollowResponse follow(String token, FollowRequest followRequest) {
        Member me = jwtTokenProvider.getMember(token);
        Long yourId = followRequest.getYourId();
        Member you = memberRepository.findById(yourId)
                .orElseThrow(() -> new MemberNotFoundException());
        me.followMember(you);

        return new FollowResponse(me.getId(), yourId);

    }
}
