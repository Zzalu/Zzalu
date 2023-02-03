package com.samsamoo.zzalu.gifs.repository;

import com.samsamoo.zzalu.gifs.entity.Gifs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GifsRepository extends JpaRepository<Gifs, Long> {
    List<Gifs> findTop90ByOrderByLikeCountDesc();
}
