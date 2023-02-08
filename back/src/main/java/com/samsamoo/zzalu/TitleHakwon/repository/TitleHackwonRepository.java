package com.samsamoo.zzalu.TitleHakwon.repository;

import com.samsamoo.zzalu.TitleHakwon.entity.TitleHakwon;
import com.samsamoo.zzalu.TitleHakwon.enums.TitleHakwonState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitleHackwonRepository extends JpaRepository<TitleHakwon, String> {


    TitleHakwon findTitleHakwonById(Long id);

    TitleHakwon findTitleHakwonByOpenDate(String openDate);

    List<TitleHakwon> findTitleHakwonByState(TitleHakwonState status);
}
