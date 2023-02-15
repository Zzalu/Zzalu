package com.samsamoo.zzalu.tempGif.repo;

import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.tempGif.entity.TempGif;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TempGifRepository extends JpaRepository<TempGif, Long> {
    Optional<TempGif> findById(Long id);
    List<TempGif> findAll();
    List<TempGif> findAllByAllowedMembersContaining(Member member);
}
