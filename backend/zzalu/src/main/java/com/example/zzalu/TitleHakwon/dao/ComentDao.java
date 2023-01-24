package com.example.zzalu.TitleHakwon.dao;

import com.example.zzalu.TitleHakwon.model.TitleHakwon;
import com.example.zzalu.TitleHakwon.model.TitleHakwonComent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentDao extends JpaRepository<TitleHakwonComent, String> {
}
