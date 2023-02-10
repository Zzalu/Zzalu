package com.samsamoo.zzalu.gifs.service;

import com.samsamoo.zzalu.advice.NotFoundException;
import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.board.dto.GifInfo;
import com.samsamoo.zzalu.board.dto.GifList;
import com.samsamoo.zzalu.gifs.dto.GifsUpdateDto;
import com.samsamoo.zzalu.gifs.entity.Gifs;
import com.samsamoo.zzalu.gifs.repository.GifsRepository;
import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.statistics.entity.GifStatistics;
import com.samsamoo.zzalu.statistics.entity.MemberTagStatistics;
import com.samsamoo.zzalu.statistics.repository.GifStatisticsRepository;
import com.samsamoo.zzalu.statistics.repository.MemberTagStatisticsRepository;
import com.samsamoo.zzalu.statistics.service.MemberTagStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GifsService {

    private final GifsRepository gifRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberTagStatisticsRepository memberTagStatisticsRepository;
    private final GifStatisticsRepository gifStatisticsRepository;

    public List<Gifs> findAllGif() {
        return gifRepository.findAll();
    }

    public Optional<Gifs> findById(Long id) {
        return gifRepository.findById(id);
    }

    public List<Gifs> findTop30ByOrderByLikeCountDesc() {
        return gifRepository.findTop30ByOrderByLikeCountDesc();
    }

    public List<Gifs> findByTags(String searchKeyword) { return gifRepository.findByTagsContains(searchKeyword); }

    public Long counyBy(){
        return gifRepository.countBy();
    }

    public List<Gifs> findByIdIn(List<Long> gifIds) {
        return gifRepository.findByIdIn(gifIds);
    }

    public GifList recommendCustomGif(String token) {
        Member member = jwtTokenProvider.getMember(token);
        // 사용자가 많이 사용한 태그 상위 4개
        List<MemberTagStatistics> memberTagStatisticsList = memberTagStatisticsRepository.findTop5ByMemberIdOrderByCountDesc(member.getId());
        // 정렬
        List<Gifs> gifList = new ArrayList<>();
        for (MemberTagStatistics statistics: memberTagStatisticsList) {
            gifList.addAll(gifRepository.findByTagsContains(statistics.getTag()));
        }

        // 랜덤으로 30개 뽑기
        Collections.shuffle(gifList);
        if(gifList.size() >= 30) {
            gifList = gifList.subList(0,30);
        }

        List<GifInfo> gifInfos = new ArrayList<>();

        for (Gifs gif : gifList) {
//            Gifs gif = gifRepository.findById(statistics.getGifId())
//                    .orElseThrow(()-> new NotFoundException("gif를 찾을 수 없습니다."));
            gifInfos.add(new GifInfo(gif.getId(), gif.getGifPath()));
        }
        return new GifList(gifInfos);
    }

//    public Gifs gifDetailUpdate(GifsUpdateDto gifsUpdateDto){
//        Optional<Gifs> optionalGifs = findById(gifsUpdateDto.getId());
//        if(optionalGifs.isPresent()) {
//            Gifs gifs = optionalGifs.get();
//            gifs.setTags(gifsUpdateDto.getTags());
//            gifs.setSources(gifsUpdateDto.getSources());
//            gifs.setSourcesPostUrl(gifsUpdateDto.getSourcesPostUrl());
//            gifs.setSourcesTld(gifsUpdateDto.getSourcesTld());
//            gifs.setRelationsVideo(gifsUpdateDto.getRelationsVideo());
//            gifs.setDescription(gifsUpdateDto.getDescription());
//            gifRepository.save(gifs);
//            return gifs;
//        } else {
//            return null;
//        }
//    }
}

