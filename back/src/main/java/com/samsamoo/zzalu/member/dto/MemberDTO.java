package com.samsamoo.zzalu.member.dto;

import com.samsamoo.zzalu.member.entity.Member;
import lombok.Getter;


@Getter
public class MemberDTO {
    private Long id;
    private String username;
    private String userEmail;
    private String nickname;
    private boolean accountNonLocked;
    private boolean enabled;
    private boolean accountNonExpired;


    public MemberDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.userEmail = member.getUserEmail();
        this.nickname = member.getNickname();
        this.accountNonLocked = member.isAccountNonLocked();
        this.enabled = member.isEnabled();
        this.accountNonExpired = member.isAccountNonExpired();
    }
}
