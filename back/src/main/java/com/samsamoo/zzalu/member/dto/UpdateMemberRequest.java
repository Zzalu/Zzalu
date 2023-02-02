package com.samsamoo.zzalu.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateMemberRequest {
    private String profilePath;
    private String nickname;
    private String profileMessage;
}
