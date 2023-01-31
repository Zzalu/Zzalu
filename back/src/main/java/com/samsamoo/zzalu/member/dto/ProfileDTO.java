package com.samsamoo.zzalu.member.dto;

import com.samsamoo.zzalu.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class ProfileDTO {
    private Long id;
    private String username;
    private String nickname;
    private String userEmail;
    private LocalDate enrollDate;
    private String profilePath;
    private int followingCnt;
    private int followerCnt;

    public ProfileDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.userEmail = member.getUserEmail();
        this.nickname = member.getNickname();
        this.enrollDate = LocalDate.from(member.getEnrollDate());
        this.profilePath = member.getProfilePath();
        this.followingCnt = member.getFollowing().size();
        this.followerCnt = member.getFollower().size();
    }
}
