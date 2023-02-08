package com.samsamoo.zzalu.statistics.service;

import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.statistics.entity.MemberTagStatistics;
import com.samsamoo.zzalu.statistics.repository.MemberTagStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberTagStatisticsService {

    private final MemberTagStatisticsRepository memberTagStatisticsRepository;

    public Optional<MemberTagStatistics> findByTagAndMemberId(String tag, Long memberId) {
        return memberTagStatisticsRepository.findByTagAndMemberId(tag, memberId);
    }

    public List<MemberTagStatistics> findAllByMemberId(Long memberId) {
        return memberTagStatisticsRepository.findAllByMemberId(memberId);
    }

}
