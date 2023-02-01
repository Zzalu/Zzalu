//package com.samsamoo.zzalu.gif.service;
//
//import com.samsamoo.zzalu.gif.entity.GiphyGif;
//import com.samsamoo.zzalu.gif.repository.GiphyGifRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class GiphyGifService {
//    private final GiphyGifRepository giphyGifRepository;
//
//    public List<GiphyGif> findAllGif() {
//        return giphyGifRepository.findAll();
//    }
//
//    public Optional<GiphyGif> findById(Long id) {
//        return giphyGifRepository.findById(id);
//    }
//
////    public List<GiphyGif> findMainGif() {return giphyGifRepository.findTop90ByOrderByLikeCountDesc();}
//}
