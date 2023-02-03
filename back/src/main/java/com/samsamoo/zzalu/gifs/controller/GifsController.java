package com.samsamoo.zzalu.gifs.controller;

import com.samsamoo.zzalu.gifs.entity.Gifs;
import com.samsamoo.zzalu.gifs.service.GifsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gif")
public class GifsController {

    private final GifsService giphyGifService;

    @GetMapping("/gifs")
    public ResponseEntity<List<Gifs>> mainPage() {
        List<Gifs> giphyGifList = giphyGifService.findAllGif();
        return ResponseEntity.ok().body(giphyGifList);
    }

    @PostMapping("/gif")
    public ResponseEntity<Optional<Gifs>> findById(@RequestParam("gifId") Long gifId) {
        Optional<Gifs> giphyGifList = giphyGifService.findById(gifId);
        return ResponseEntity.ok().body(giphyGifList);
    }

    @GetMapping("/main/gifs")
    public ResponseEntity<List<Gifs>> findTop90() {
        return ResponseEntity.ok().body(giphyGifService.findTop90());
    }

}
