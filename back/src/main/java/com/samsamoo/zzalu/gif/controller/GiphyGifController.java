package com.samsamoo.zzalu.gif.controller;

import com.samsamoo.zzalu.gif.Entity.GiphyGif;
import com.samsamoo.zzalu.gif.service.GiphyGifService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/giphy")
public class GiphyGifController {

    private final GiphyGifService giphyGifService;

    @PostMapping("/main")
    public ResponseEntity<List<GiphyGif>> mainPage() {
        List<GiphyGif> giphyGifList = giphyGifService.findAllGif();
        return ResponseEntity.ok().body(giphyGifList);
    }

    @PostMapping("/findById")
    public ResponseEntity<Optional<GiphyGif>> findById() {
        Optional<GiphyGif> giphyGifList = giphyGifService.findById(0L);
        return ResponseEntity.ok().body(giphyGifList);
    }

}
