package com.samsamoo.zzalu.TitleHakwon.dto;

import com.samsamoo.zzalu.TitleHakwon.entity.TitleHakwon;
import com.samsamoo.zzalu.TitleHakwon.enums.TitleHakwonState;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@Getter
public class TitleHakwonResponse {

    /** 제목학원 open 날짜 **/

    private Long titleHakwonId;
    private String openData;

    private String zzalurl;

    private TitleHakwonState state;


    public TitleHakwonResponse(TitleHakwon titleHakwon) {
        this.titleHakwonId = titleHakwon.getId();
        this.openData = titleHakwon.getOpenDate();
        this.zzalurl = titleHakwon.getZzulUrl();
        this.state = titleHakwon.getState();

    }

    public  static List<TitleHakwonResponse> convertToDtoList (List<TitleHakwon> titleHakwonList){
        List<TitleHakwonResponse> titleHakwonResponseList = new ArrayList<>();

        for(TitleHakwon titleHakwon:titleHakwonList){
            titleHakwonResponseList.add(new TitleHakwonResponse(titleHakwon));
        }
        return titleHakwonResponseList;
    }
}
