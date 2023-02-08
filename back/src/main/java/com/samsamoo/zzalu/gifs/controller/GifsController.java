package com.samsamoo.zzalu.gifs.controller;

import com.samsamoo.zzalu.gifs.dto.GifsUpdateDto;
import com.samsamoo.zzalu.gifs.entity.Gifs;
import com.samsamoo.zzalu.gifs.service.GifsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/gif")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GifsController {

    private final GifsService gifsService;

    @GetMapping("/gifs")
    public ResponseEntity<List<Gifs>> mainPage() {
        List<Gifs> giphyGifList = gifsService.findAllGif();
        return ResponseEntity.ok().body(giphyGifList);
    }

    @GetMapping("/gif")
    public ResponseEntity<Optional<Gifs>> findById(@RequestParam("gifId") Long gifId) {
        Optional<Gifs> gif = gifsService.findById(gifId);
        return ResponseEntity.ok().body(gif);
    }

    @GetMapping("/main/gifs")
    public ResponseEntity<List<Gifs>> findTop30ByOrderByLikeCountDesc() {
        return ResponseEntity.ok().body(gifsService.findTop30ByOrderByLikeCountDesc());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Gifs>> findByTags(@RequestParam("searchKeyword") String searchKeyword) {
        return ResponseEntity.ok().body(gifsService.findByTags(searchKeyword));
    }

    @PostMapping("/randoms")
    public ResponseEntity<List<Gifs>> randomGifs(@RequestBody Map<String, List<Long>> map) {
        Long columnCount = gifsService.counyBy();
        List<Long> exceptIds = map.get("exceptIds");

        HashMap<Long, Boolean> duplicateCheck = new HashMap<>();
        HashMap<Long, Boolean> randomGifIds = new HashMap<>();

        for (long index = 0; index < exceptIds.size(); ++index) {
            duplicateCheck.put(exceptIds.get((int) index), true);
        }

        while (randomGifIds.size() < 30) {
            long randomId = (long) (Math.random() * columnCount + 1);
            if(!duplicateCheck.containsKey(randomId))
                randomGifIds.put(randomId, true);
        }

        List<Long> gifIds = new ArrayList<>(randomGifIds.keySet());
        List<Gifs> gifsList = gifsService.findByIdIn(gifIds);
        System.out.println("gifList : " + gifsList);
        return ResponseEntity.ok().body(gifsList);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Gifs>> userGifs() {
        Long columnCount = gifsService.counyBy();

        HashMap<Long, Boolean> randomGifIds = new HashMap<>();

        while (randomGifIds.size() < 90) {
            long randomId = (long) (Math.random() * columnCount + 1);
            randomGifIds.put(randomId, true);
        }

        List<Long> gifIds = new ArrayList<>(randomGifIds.keySet());
        List<Gifs> gifsList = gifsService.findByIdIn(gifIds);
        System.out.println("gifList : " + gifsList);
        return ResponseEntity.ok().body(gifsList);
    }

    @PutMapping("/gif")
    public ResponseEntity<Gifs> updateGifs(@RequestBody GifsUpdateDto gifsUpdateDto) {
        System.out.println(gifsUpdateDto.toString());
        Gifs gifs = gifsService.gifDetailUpdate(gifsUpdateDto);
        return ResponseEntity.ok().body(gifs);
    }

}

