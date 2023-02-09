package com.samsamoo.zzalu.statistics.repository;

import com.samsamoo.zzalu.statistics.entity.GifStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GifStatisticsRepository extends JpaRepository<GifStatistics, Long> {
    Optional<GifStatistics> findByGifId(Long id);
}
