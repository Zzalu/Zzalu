package com.samsamoo.zzalu.member.dto.follow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnfollowResponse {
    private Long unfollowerId;
    private Long unfollowingId;


}
