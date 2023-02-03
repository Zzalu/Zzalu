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

    private final GifsRepository giphyGifRepository;

    public List<Gifs> findAllGif() {
        return giphyGifRepository.findAll();
    }

    public Optional<Gifs> findById(Long id) {
        return giphyGifRepository.findById(id);
    }

    public List<Gifs> findTop90() {
        return giphyGifRepository.findTop90ByOrderByLikeCountDesc();
    }

}
