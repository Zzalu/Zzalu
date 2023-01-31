package com.example.zzalu.TitleHakwon.repository;

import com.example.zzalu.TitleHakwon.entity.TitleHakwon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleHackwonRepository extends JpaRepository<TitleHakwon, String> {


    TitleHakwon findTitleHakwonById(Long id);

    TitleHakwon findTitleHakwonByOpenDate(String openDate);
}
