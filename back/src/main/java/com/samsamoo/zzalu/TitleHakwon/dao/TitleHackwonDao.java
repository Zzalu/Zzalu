package com.samsamoo.zzalu.TitleHakwon.dao;

import com.samsamoo.zzalu.TitleHakwon.model.TitleHakwon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleHackwonDao extends JpaRepository<TitleHakwon, String> {


    TitleHakwon findTitleHakwonById(Long id);
}
