package com.samsamoo.zzalu.AwardRecord.dto;


import com.samsamoo.zzalu.AwardRecord.entity.AwardRecord;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AwardResponse {

    int rank;
    Long titleHakwonId;

    public AwardResponse(AwardRecord awardRecord) {
        this.rank = awardRecord.getRank();
        this.titleHakwonId = awardRecord.getTitleHakwon().getId();
    }

    public static List<AwardResponse> converToDtoList(List<AwardRecord> awardRecordList){

        List<AwardResponse> awardResponseList = new ArrayList<>();
        for(AwardRecord awardRecord :awardRecordList){
            awardResponseList.add( new AwardResponse(awardRecord));
        }

        return awardResponseList;


    }
}
