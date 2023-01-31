package com.samsamoo.zzalu.member.dto;

import com.samsamoo.zzalu.member.entity.Member;

import java.time.LocalDate;

public class ProfileDTO {
    private Long id;
    private String username;
    private String nickname;
    private String userEmail;
    private LocalDate enrollDate;
    private String profilePath;
    private int followingCnt;
    private int followerCnt;

    private Boolean followState;

    public ProfileDTO(Member member, Boolean isFollowing) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.userEmail = member.getUserEmail();
        this.nickname = member.getNickname();
        this.enrollDate = LocalDate.from(member.getEnrollDate());
        this.profilePath = member.getProfilePath();
        this.followingCnt = member.getFollowing().size();
        this.followerCnt = member.getFollower().size();
        this.followState = isFollowing;
    }
}
