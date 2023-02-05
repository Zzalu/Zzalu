package com.samsamoo.zzalu.gifs.service;

import com.samsamoo.zzalu.gifs.entity.Gifs;
import com.samsamoo.zzalu.gifs.repository.GifsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GifsService {

    private final GifsRepository GifRepository;

    public List<Gifs> findAllGif() {
        return GifRepository.findAll();
    }

    public Optional<Gifs> findById(Long id) {
        return GifRepository.findById(id);
    }

    public List<Gifs> findTop90() {
        return GifRepository.findTop90ByOrderByLikeCountDesc();
    }

    public List<Gifs> findByTags(String searchKeyword) { return GifRepository.findByTagsContains(searchKeyword); }

    public Long counyBy(){
        return GifRepository.countBy();
    }

    public List<Gifs> findByIdIn(List<Long> gifIds) {
        return GifRepository.findByIdIn(gifIds);
    }
}

