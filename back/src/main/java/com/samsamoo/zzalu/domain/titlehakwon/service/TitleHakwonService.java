package com.samsamoo.zzalu.domain.titlehakwon.service;

import com.samsamoo.zzalu.domain.award.entity.AwardRecord;
import com.samsamoo.zzalu.domain.award.repository.AwardRecordRepository;
import com.samsamoo.zzalu.domain.titlehakwon.dto.TitleHakwonDetailResponse;
import com.samsamoo.zzalu.domain.titlehakwon.dto.TitleHakwonResponse;
import com.samsamoo.zzalu.domain.titlehakwon.entity.Comment;
import com.samsamoo.zzalu.domain.titlehakwon.entity.TitleHakwon;
import com.samsamoo.zzalu.domain.titlehakwon.enums.TitleHakwonState;
import com.samsamoo.zzalu.domain.titlehakwon.exception.TitleHakwonException;
import com.samsamoo.zzalu.domain.titlehakwon.repository.CommentRepository;
import com.samsamoo.zzalu.domain.titlehakwon.repository.TitleHackwonRepository;
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

    public TitleHakwonDetailResponse getTitleHakwonInfo(String openDate){

        TitleHakwon titleHakwon = titleHackwonRepository.findTitleHakwonByOpenDate(openDate).orElseThrow(()-> new TitleHakwonException());


       return new TitleHakwonDetailResponse(titleHakwon);

    }

    /**
     * 매일 아침 10시 제목학원 오픈
     */

    /* @Scheduled(cron = "0 0 10 * * *")*/
    public void openTitleHakwon(){
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = current.format(formatter);

        TitleHakwon titleHakwon = titleHackwonRepository.findTitleHakwonByOpenDate(date).orElseThrow(()-> new TitleHakwonException());

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

        TitleHakwon titleHakwon = titleHackwonRepository.findTitleHakwonByOpenDate(date).orElseThrow(()-> new TitleHakwonException());


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

    /**
     * 역대 제목학원 리스트 조회하기
     */

    public List<TitleHakwonResponse> getTitleHakwonList(){
        List<TitleHakwon> titleHakwonList = titleHackwonRepository.findTitleHakwonByState(TitleHakwonState.DONE);
        return TitleHakwonResponse.convertToDtoList(titleHakwonList);
    }



}
