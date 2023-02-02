package com.samsamoo.zzalu.gifs.repository;

import com.samsamoo.zzalu.gifs.entity.GiphyGifs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiphyGifsRepository extends JpaRepository<GiphyGifs, Long> {
    List<GiphyGifs> findTop90ByOrderByLikeCountDesc();
}
