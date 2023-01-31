package com.samsamoo.zzalu.gif.controller;

import com.samsamoo.zzalu.gif.Entity.GiphyGif;
import com.samsamoo.zzalu.gif.dto.GiphyGifDto;
import com.samsamoo.zzalu.gif.service.GiphyGifService;
import com.samsamoo.zzalu.member.dto.UniqueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/giphy")
public class GiphyGifController {

    private final GiphyGifService giphyGifService;

    @PostMapping("/main")
    public ResponseEntity<List<GiphyGif>> mainPage() {
        List<GiphyGif> giphyGifList = giphyGifService.findAllGif();
        System.out.println("=============================");
        System.out.println("GiphyGifController - mainPage");
        System.out.println(giphyGifList);
        return ResponseEntity.ok().body(giphyGifList);
    }

}
