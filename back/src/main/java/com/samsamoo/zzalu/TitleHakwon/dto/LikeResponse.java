package com.samsamoo.zzalu.TitleHakwon.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class LikeResponse {

    private Long id ;

    private int likeNum;

    public LikeResponse(Long id, int likeNum) {
        this.id = id;
        this.likeNum = likeNum;
    }
}
