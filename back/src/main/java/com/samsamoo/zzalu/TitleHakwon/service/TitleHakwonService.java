package com.samsamoo.zzalu.TitleHakwon.service;

import com.samsamoo.zzalu.AwardRecord.entity.AwardRecord;
import com.samsamoo.zzalu.AwardRecord.repository.AwardRecordRepository;
import com.samsamoo.zzalu.TitleHakwon.dto.TitleHakwonResponse;
import com.samsamoo.zzalu.TitleHakwon.entity.Comment;
import com.samsamoo.zzalu.TitleHakwon.entity.TitleHakwon;
import com.samsamoo.zzalu.TitleHakwon.enums.TitleHakwonState;
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
    private final CommentRepository commentRepository;

    private final AwardRecordRepository awardRecordRepository;


    /**
     * 제목학원 정보 조회
     */

    public TitleHakwonResponse getTitleHakwonInfo(String openDate){
        if(titleHackwonRepository.findTitleHakwonByOpenDate(openDate)==null){

        }
       return new TitleHakwonResponse(titleHackwonRepository.findTitleHakwonByOpenDate(openDate));

    }

    /**
     * 매일 아침 10시 제목학원 오픈
     */

    /* @Scheduled(cron = "0 0 10 * * *")*/
    public void openTitleHakwon(){
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = current.format(formatter);

        TitleHakwon titleHakwon = titleHackwonRepository.findTitleHakwonByOpenDate(date);

        if(titleHakwon.getState().getCode().equals("N")) {
            System.out.println("오늘의 제목학원 open!");
            titleHakwon.setState(TitleHakwonState.PROCEED);
            titleHackwonRepository.save(titleHakwon);

        }else{
            System.out.println("제목학원이 이미 연결되어있거나 끝났습니다.");
        }
    }



    /**
     * 매일 저녁 10시 제목학원 닫기 , 좋아요 1/2/3/등 유저에게 수상 기록 저장
     */

    /* @Scheduled(cron = "0 3 21 * * *")*/
    public  void setRank () {
        //오늘 날짜를 알아야함

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = current.format(formatter);

        TitleHakwon titleHakwon = titleHackwonRepository.findTitleHakwonByOpenDate(date);


        //상위 3개의 댓글 가져오기
        List<Comment> comments = commentRepository.findTop3ByTitleHakwonIdAndLikeNumGreaterThanOrderByLikeNumDesc(titleHakwon.getId(), 0);

        for (int i = 0; i < comments.size(); i++) {

            AwardRecord awardRecord = AwardRecord.builder()
                    .member(comments.get(i).getMember())
                    .titleHakwon(comments.get(i).getTitleHakwon())
                    .rank(i + 1)
                    .build();
            awardRecordRepository.save(awardRecord);

        }


    }



}
