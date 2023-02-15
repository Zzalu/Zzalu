package com.samsamoo.zzalu.gifs.service;

import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.board.dto.GifInfo;
import com.samsamoo.zzalu.board.dto.GifList;
import com.samsamoo.zzalu.gifs.entity.Gifs;
import com.samsamoo.zzalu.gifs.repository.GifsRepository;
import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.statistics.entity.MemberTagStatistics;
import com.samsamoo.zzalu.statistics.repository.GifStatisticsRepository;
import com.samsamoo.zzalu.statistics.repository.MemberTagStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Gifs> findByTags(String searchKeyword) { return gifRepository.findByTagsContains(searchKeyword); }

    public Long counyBy(){
        return gifRepository.countBy();
    }

    public List<Gifs> findByIdIn(List<Long> gifIds) {
        return gifRepository.findByIdIn(gifIds);
    }

    public List<Gifs> recommendCustomGif(String token) {
        Member member = jwtTokenProvider.getMember(token);
        // 사용자가 많이 사용한 태그 상위 4개
        List<MemberTagStatistics> memberTagStatisticsList = memberTagStatisticsRepository.findTop4ByMemberIdOrderByCountDesc(member.getId());
        // 정렬
        Set<Gifs> gifSet = new HashSet<>();
        for (MemberTagStatistics statistics: memberTagStatisticsList) {
            gifSet.addAll(gifRepository.findTop20ByTagsContainsOrderByVisitedCountDesc(statistics.getTag()));
        }
        // 랜덤으로 30개 뽑기
        List<Gifs> gifList = new ArrayList<>(gifSet);
        Collections.shuffle(gifList);
        if(gifList.size() >= 30) {
            gifList = gifList.subList(0,30);
        }
        return gifList;
    }
}

