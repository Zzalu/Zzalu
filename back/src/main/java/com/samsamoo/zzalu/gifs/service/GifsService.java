package com.samsamoo.zzalu.gifs.service;

import com.samsamoo.zzalu.gifs.dto.GifsUpdateDto;
import com.samsamoo.zzalu.gifs.entity.Gifs;
import com.samsamoo.zzalu.gifs.repository.GifsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GifsService {

    private final GifsRepository gifRepository;

    public List<Gifs> findAllGif() {
        return gifRepository.findAll();
    }

    public Optional<Gifs> findById(Long id) {
        return gifRepository.findById(id);
    }

    public List<Gifs> findTop30ByOrderByLikeCountDesc() {
        return gifRepository.findTop30ByOrderByLikeCountDesc();
    }

    public List<Gifs> findByTags(String searchKeyword) { return gifRepository.findByTagsContains(searchKeyword); }

    public Long counyBy(){
        return gifRepository.countBy();
    }

    public List<Gifs> findByIdIn(List<Long> gifIds) {
        return gifRepository.findByIdIn(gifIds);
    }

    public Gifs gifDetailUpdate(GifsUpdateDto gifsUpdateDto){
        Optional<Gifs> optionalGifs = findById(gifsUpdateDto.getId());
        if(optionalGifs.isPresent()) {
            Gifs gifs = optionalGifs.get();
            gifs.setTags(gifsUpdateDto.getTags());
            gifs.setSources(gifsUpdateDto.getSources());
            gifs.setSourcesPostUrl(gifsUpdateDto.getSourcesPostUrl());
            gifs.setSourcesTld(gifsUpdateDto.getSourcesTld());
            gifs.setRelationsVideo(gifsUpdateDto.getRelationsVideo());
            gifs.setDescription(gifsUpdateDto.getDescription());
            gifRepository.save(gifs);
            return gifs;
        } else {
            return null;
        }
    }
}

