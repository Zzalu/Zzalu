package com.samsamoo.zzalu.gif.repository;

import com.samsamoo.zzalu.gif.Entity.GiphyGif;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GiphyGifRepository extends JpaRepository<GiphyGif, Long> {
}
