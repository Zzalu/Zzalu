package com.samsamoo.zzalu.member.dto.follow;

import com.samsamoo.zzalu.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MyFollowMemberDTO {
    private Long id;
    private String username;
    private String nickname;
    private String profilePath;

    public MyFollowMemberDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.profilePath = member.getProfilePath();
    }
}
