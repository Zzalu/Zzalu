package com.samsamoo.zzalu.TitleHakwon.dao;

import com.samsamoo.zzalu.TitleHakwon.model.TitleHakwon;
import com.samsamoo.zzalu.TitleHakwon.model.TitleHakwonComent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentDao extends JpaRepository<TitleHakwonComent, String> {
}
