package com.samsamoo.zzalu.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FollowingDTO {
    private Long id;
    private String username;
    private String nickname;
}
