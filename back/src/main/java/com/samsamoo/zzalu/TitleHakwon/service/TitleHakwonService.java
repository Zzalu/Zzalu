package com.samsamoo.zzalu.TitleHakwon.service;

import com.samsamoo.zzalu.AwardRecord.entity.AwardRecord;
import com.samsamoo.zzalu.AwardRecord.repository.AwardRecordRepository;
import com.samsamoo.zzalu.TitleHakwon.dto.TitleHakwonResponse;
import com.samsamoo.zzalu.TitleHakwon.entity.Comment;
import com.samsamoo.zzalu.TitleHakwon.entity.TitleHakwon;
import com.samsamoo.zzalu.TitleHakwon.repository.CommentRepository;
import com.samsamoo.zzalu.TitleHakwon.repository.TitleHackwonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RequiredArgsConstructor  //얘가 자동으로 생성자 주입해줌
@Repository
public class TitleHakwonService {
    private final TitleHackwonRepository titleHackwonRepository;

    public TitleHakwonResponse getTitleHakwonInfo(String openDate){
        if(titleHackwonRepository.findTitleHakwonByOpenDate(openDate)==null){

        }
       return new TitleHakwonResponse(titleHackwonRepository.findTitleHakwonByOpenDate(openDate));

    }





}
