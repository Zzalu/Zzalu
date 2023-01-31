package com.samsamoo.zzalu.gif.service;

import com.samsamoo.zzalu.gif.Entity.GiphyGif;
import com.samsamoo.zzalu.gif.dto.GiphyGifDto;
import com.samsamoo.zzalu.gif.repository.GiphyGifRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiphyGifService {
    private final GiphyGifRepository giphyGifRepository;

    public List<GiphyGif> findAllGif() {
        return giphyGifRepository.findAll();
    }

}
