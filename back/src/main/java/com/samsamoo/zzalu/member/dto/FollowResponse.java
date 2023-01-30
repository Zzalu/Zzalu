package com.samsamoo.zzalu.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FollowResponse {
    private Long myId;
    private Long yourId;

}
