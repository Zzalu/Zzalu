package com.samsamoo.zzalu.gifs.controller;

import com.samsamoo.zzalu.gifs.entity.GiphyGifs;
import com.samsamoo.zzalu.gifs.service.GiphyGifsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/giphy")
public class GifsController {

    private final GiphyGifsService giphyGifService;

    @GetMapping("/gifs")
    public ResponseEntity<List<GiphyGifs>> mainPage() {
        List<GiphyGifs> giphyGifList = giphyGifService.findAllGif();
        return ResponseEntity.ok().body(giphyGifList);
    }

    @PostMapping("/gif")
    public ResponseEntity<Optional<GiphyGifs>> findById(@RequestParam("gifId") Long gifId) {
        Optional<GiphyGifs> giphyGifList = giphyGifService.findById(gifId);
        return ResponseEntity.ok().body(giphyGifList);
    }

    @GetMapping("/main/gifs")
    public ResponseEntity<List<GiphyGifs>> findTop90() {
        return ResponseEntity.ok().body(giphyGifService.findTop90());
    }

}
