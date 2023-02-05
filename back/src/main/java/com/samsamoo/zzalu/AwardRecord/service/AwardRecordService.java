package com.samsamoo.zzalu.AwardRecord.service;

import com.samsamoo.zzalu.AwardRecord.entity.AwardRecord;
import com.samsamoo.zzalu.AwardRecord.repository.AwardRecordRepository;
import com.samsamoo.zzalu.TitleHakwon.entity.Comment;
import com.samsamoo.zzalu.TitleHakwon.entity.TitleHakwon;
import com.samsamoo.zzalu.TitleHakwon.repository.CommentRepository;
import com.samsamoo.zzalu.TitleHakwon.repository.TitleHackwonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class AwardRecordService {

    private final TitleHackwonRepository titleHackwonRepository;
    private final CommentRepository commentRepository;

    private final AwardRecordRepository awardRecordRepository;

    /** 제목 학원이 끝나고 상위 3명을 수상한다. **/

    /** 매일 오후 10시 에 종료 **/
   /* @Scheduled(cron = "0 3 21 * * *")*/
    public  void setRank (){
        //오늘 날짜를 알아야함

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = current.format(formatter);

        System.out.println(date + "오늘 날짜 ");




        TitleHakwon titleHakwon = titleHackwonRepository.findTitleHakwonByOpenDate("2023-02-02");


        //상위 3개의 댓글 가져오기
        List<Comment> comments = commentRepository.findTop3ByTitleHakwonIdAndLikeNumGreaterThanOrderByLikeNumDesc(titleHakwon.getId(), 0);

        for(int i = 0 ; i < comments.size();i++){

            AwardRecord awardRecord = AwardRecord.builder()
                    .member(comments.get(i).getMember())
                    .titleHakwon(comments.get(i).getTitleHakwon())
                    .rank(i+1)
                    .build();


            awardRecordRepository.save(awardRecord);

        }


    }
}
