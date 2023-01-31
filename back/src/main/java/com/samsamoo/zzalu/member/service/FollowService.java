package com.samsamoo.zzalu.member.service;

import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.member.dto.follow.FollowMemberDTO;
import com.samsamoo.zzalu.member.dto.follow.FollowResponse;
import com.samsamoo.zzalu.member.dto.follow.MyFollowMemberDTO;
import com.samsamoo.zzalu.member.dto.follow.UnfollowResponse;
import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.member.exception.FollowException;
import com.samsamoo.zzalu.member.exception.InvalidTokenException;
import com.samsamoo.zzalu.member.exception.MemberNotFoundException;
import com.samsamoo.zzalu.member.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowService {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public FollowResponse follow(String token, Long memberId) {
        // 토큰 검증
        checkToken(token);

        // 팔로우 과정
        Member me = jwtTokenProvider.getMember(token);
        Member you = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException());

        // 자신 팔로우 막기
        checkSelfFollow(me, you);

        me.followMember(you);
        memberRepository.save(me);
        memberRepository.save(you);

        return new FollowResponse(me.getId(), you.getId());

    }

    public UnfollowResponse unfollow(String token, Long memberId) {
        // 토큰 검증
        checkToken(token);

        // 언 팔로우 과정
        Member me = jwtTokenProvider.getMember(token);
        Member you = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException());
        // 자신 언 팔로우 막기
        checkSelfFollow(me, you);

        me.unfollowMember(you);
        memberRepository.save(me);
        memberRepository.save(you);
        return new UnfollowResponse(me.getId(), you.getId());

    }

    // 토큰 검증 실패 시 에외 발생
    private void checkToken(String token) {
        if (!jwtTokenProvider.validateToken(token)) {
            throw new InvalidTokenException();
        }
    }

    // 자신을 팔로우, 언팔로우 요청 예외 발생
     private void checkSelfFollow(Member me, Member you) {
         if (me.equals(you)) {
             throw new FollowException("자신을 팔로우하거나 언팔로우 할 수 없습니다.");
         }
    }

    public List<Object> getFollowingList(String token, Long memberId) {
        // 토큰 검증
        checkToken(token);

        // token에 저장된 Member > 요청한 사람
        Member me = jwtTokenProvider.getMember(token);

        // 팔로잉이 궁금한 Member
        Member target = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException());

        // 내가 팔로잉하고 있는 맴버들 불러오기
        List<Member> targetFollowings =  target.getFollowing();

        List<Object> followingList = new ArrayList<>();

        // 둘이 일치할 경우 > MyFollowMemberDTO 사용
        if(me.equals(target)) {
            for (Iterator<Member> itr = targetFollowings.iterator(); itr.hasNext();) {
                Member entity = itr.next();
                // entity를 dto로 변환
                MyFollowMemberDTO dto = new MyFollowMemberDTO(entity);
                followingList.add(dto);
            }

        } else {
            // 둘이 다를 경우 > FollowMemberDTO 사용
            for (Iterator<Member> itr = targetFollowings.iterator(); itr.hasNext();) {
                Member entity = itr.next();
                // 내가 팔로우 하고 있는지 여부를 넣어줘야 한다.
                List<Member> followers = entity.getFollower();
                Boolean isFollowing = followers.contains(me);
                // entity를 dto로 변환
                FollowMemberDTO dto = new FollowMemberDTO(entity, isFollowing);
                followingList.add(dto);
            }
        }
        return followingList;
    }

    public List<Object> getFollowerList(String token, Long memberId) {
        // 토큰 검증
        checkToken(token);

        // token에 저장된 Member > 요청한 사람
        Member me = jwtTokenProvider.getMember(token);

        // 팔로워가 궁금한 Member
        Member target = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException());

        // target의 팔로워 맴버들 불러오기
        List<Member> targetFollowers =  target.getFollower();

        List<Object> followerList = new ArrayList<>();

        // 둘이 일치할 경우 > MyFollowMemberDTO 사용
        if(me.equals(target)) {
            for (Iterator<Member> itr = targetFollowers.iterator(); itr.hasNext();) {
                Member entity = itr.next();
                // entity를 dto로 변환
                MyFollowMemberDTO dto = new MyFollowMemberDTO(entity);
                followerList.add(dto);
            }

        } else {
            // 둘이 다를 경우 > FollowMemberDTO 사용
            for (Iterator<Member> itr = targetFollowers.iterator(); itr.hasNext();) {
                Member entity = itr.next();
                // 내가 팔로우 하고 있는지 여부를 넣어줘야 한다.
                List<Member> followers = entity.getFollower();
                Boolean isFollowing = followers.contains(me);
                // entity를 dto로 변환
                FollowMemberDTO dto = new FollowMemberDTO(entity, isFollowing);
                followerList.add(dto);
            }
        }
        return followerList;
    }
}
