package com.samsamoo.zzalu.board.controller;

import com.samsamoo.zzalu.board.dto.BoardGifs;
import com.samsamoo.zzalu.board.dto.CreateBoardResposne;
import com.samsamoo.zzalu.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
@Slf4j
public class BoardController {
    private final BoardService boardService;

    //--------------------------------------보드 생성-----------------------------------------
    @PostMapping
    public ResponseEntity<CreateBoardResposne> createBoard(@RequestHeader(value = "Authorization")String bearerToken, @RequestBody Map<String, String> request) {
        String token = bearerToken.substring(7);
        CreateBoardResposne response = boardService.createBoard(token, request.get("boardName"));
        return ResponseEntity.created(URI.create("/boards/" + response.getId())).body(response);
    }
    //--------------------------------------보드 생성-----------------------------------------
//    @PostMapping("/gifs")
//    public ResponseEntity<BoardGif> insertGifToBoard(@RequestBody BoardGif request) {
////        log.info("gifs[1]={}", request.getGifs().get(0));
//        BoardGif response = boardService.insertGifToBoard(request.getGifs());
//        return ResponseEntity.ok(response);
//    }
//
//    //--------------------------------------보드 생성----------------------------------------
//    @DeleteMapping("/gifs")
//    public ResponseEntity<BoardGif> insertGifToBoard(@RequestBody BoardGif request) {
////        log.info("gifs[1]={}", request.getGifs().get(0));
//        BoardGif response = boardService.insertGifToBoard(request.getGifs());
//        return ResponseEntity.ok(response);
//    }
    //-----------------------------------특정 짤의 보드 정보 불러오기--------------------------------
    @GetMapping(params = "gifId")
    public ResponseEntity<BoardGifs> getGifsBoard(@RequestHeader(value = "Authorization")String bearerToken, @RequestParam Long gifId) {
        String token = bearerToken.substring(7);
        BoardGifs boardGifs = boardService.getGifsBoard(token, gifId);
        return ResponseEntity.ok(boardGifs);
    }
}
