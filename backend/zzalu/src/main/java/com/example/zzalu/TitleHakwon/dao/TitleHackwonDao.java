package com.example.zzalu.TitleHakwon.dao;

import com.example.zzalu.TitleHakwon.model.TitleHakwon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleHackwonDao extends JpaRepository<TitleHakwon, String> {


    TitleHakwon findTitleHakwonById(Long id);
}
