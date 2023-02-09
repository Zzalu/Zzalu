package com.samsamoo.zzalu.TitleHakwon.dto;

import com.samsamoo.zzalu.TitleHakwon.entity.TitleHakwon;
import com.samsamoo.zzalu.TitleHakwon.enums.TitleHakwonState;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TitleHakwonDetailResponse {


    /** 제목학원 고유 아아디 **/
    private Long titleHakwonId;

    /** 오늘의 제목학원 짤 URL **/
    private String zzalUrl;

    /** 해당 제목학원의 총 댓글 개수 (대댓글 개수 포함x) **/
    private int totalComment;

    /** 제목 학원 상태 **/
    private TitleHakwonState state;


    public TitleHakwonDetailResponse(TitleHakwon titleHakwon) {
        this.titleHakwonId = titleHakwon.getId();
        this.zzalUrl = titleHakwon.getZzulUrl();
        this.state = titleHakwon.getState();
        this.totalComment = titleHakwon.getComments().size();
    }
}
