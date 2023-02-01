package com.samsamoo.zzalu.member.controller;

import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.member.dto.follow.FollowMemberDTO;
import com.samsamoo.zzalu.member.dto.follow.FollowResponse;
import com.samsamoo.zzalu.member.dto.follow.FollowStateResponse;
import com.samsamoo.zzalu.member.dto.follow.UnfollowResponse;
import com.samsamoo.zzalu.member.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping
@Slf4j
public class FollowController {
    private final JwtTokenProvider jwtTokenProvider;
    private final FollowService followService;

    //--------------------------------------팔로우-------------------------------------------
    @PutMapping("/follow/{memberId}")
    public ResponseEntity<FollowResponse> follow(@RequestHeader(value = "Authorization") String bearerToken, @PathVariable Long memberId) {
        String token = bearerToken.substring(7);
        log.info("bearerToken = {}", bearerToken);
        FollowResponse followResponse = followService.follow(token, memberId);
        return new ResponseEntity<FollowResponse>(followResponse, HttpStatus.OK);
    }

    //--------------------------------------팔로우 취소-------------------------------------------
    @DeleteMapping ("/follow/{memberId}")
    public ResponseEntity<UnfollowResponse> unfollow(@RequestHeader(value = "Authorization") String bearerToken, @PathVariable Long memberId) {
        String token = bearerToken.substring(7);
        log.info("bearerToken = {}", bearerToken);
        UnfollowResponse unfollowResponse = followService.unfollow(token, memberId);
        return new ResponseEntity<UnfollowResponse>(unfollowResponse, HttpStatus.NO_CONTENT);
    }

    //--------------------------------------팔로잉 리스트 읽기--------------------------------------
    @GetMapping ("/following/{memberId}")
    public ResponseEntity<?> getFollowingList(@RequestHeader(value = "Authorization") String bearerToken, @PathVariable Long memberId) {
        String token = bearerToken.substring(7);
        List<Object> followingList = followService.getFollowingList(token, memberId);
        return ResponseEntity.ok().body(followingList);
    }

    //--------------------------------------팔로워 리스트 읽기--------------------------------------
    @GetMapping ("/follower/{memberId}")
    public ResponseEntity<?> getFollowerList(@RequestHeader(value = "Authorization") String bearerToken, @PathVariable Long memberId) {
        String token = bearerToken.substring(7);
        List<Object> followerList = followService.getFollowerList(token, memberId);

        return ResponseEntity.ok().body(followerList);
    }

    //--------------------------------------팔로우 여부 확인----------------------------------------
    @GetMapping ("/follow-state/{memberId}")
    public ResponseEntity<FollowStateResponse> getFollowState(@RequestHeader(value = "Authorization") String bearerToken, @PathVariable Long memberId) {
        String token = bearerToken.substring(7);
        FollowStateResponse followState = followService.getFollowState(token, memberId);

        return ResponseEntity.ok().body(followState);
    }

}
