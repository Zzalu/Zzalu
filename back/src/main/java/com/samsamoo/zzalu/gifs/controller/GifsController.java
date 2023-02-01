package com.samsamoo.zzalu.gifs.controller;

import com.samsamoo.zzalu.gifs.entity.GiphyGifs;
import com.samsamoo.zzalu.gifs.service.GiphyGifsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/giphy")
public class GifsController {

    private final GiphyGifsService giphyGifService;

    @GetMapping("/all-gifs")
    public ResponseEntity<List<GiphyGifs>> mainPage() {
        List<GiphyGifs> giphyGifList = giphyGifService.findAllGif();
        return ResponseEntity.ok().body(giphyGifList);
    }

    @PostMapping("/id-gif")
    public ResponseEntity<Optional<GiphyGifs>> findById() {
        Optional<GiphyGifs> giphyGifList = giphyGifService.findById(0L);
        return ResponseEntity.ok().body(giphyGifList);
    }

    @GetMapping("/main-gifs")
    public ResponseEntity<List<GiphyGifs>> findTop90() {
        return ResponseEntity.ok().body(giphyGifService.findTop90());
    }

}
