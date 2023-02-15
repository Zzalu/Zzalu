package com.samsamoo.zzalu.gifs.service;

import com.samsamoo.zzalu.gifs.entity.GiphyGifs;
import com.samsamoo.zzalu.gifs.repository.GiphyGifsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GiphyGifsService {

    private final GiphyGifsRepository giphyGifRepository;

    public List<GiphyGifs> findAllGif() {
        return giphyGifRepository.findAll();
    }

    public Optional<GiphyGifs> findById(Long id) {
        return giphyGifRepository.findById(id);
    }

    public List<GiphyGifs> findTop90() {
        return giphyGifRepository.findTop90ByOrderByLikeCountDesc();
    }

}
