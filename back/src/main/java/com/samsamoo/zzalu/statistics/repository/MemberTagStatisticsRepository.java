package com.samsamoo.zzalu.statistics.repository;

import com.samsamoo.zzalu.statistics.entity.MemberTagStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberTagStatisticsRepository extends JpaRepository<MemberTagStatistics, Long> {
    Optional<MemberTagStatistics> findByTagAndMemberId(String Tag, Long memberId);
    List<MemberTagStatistics> findAllByMemberId(Long memberId);
}
