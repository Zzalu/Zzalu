package com.samsamoo.zzalu.board.controller;

import com.samsamoo.zzalu.board.dto.*;
import com.samsamoo.zzalu.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    //-----------------------------------특정 짤의 보드 정보 불러오기--------------------------------
    @GetMapping(params = "gifId")
    public ResponseEntity<GifsBoardList> getGifsBoard(@RequestHeader(value = "Authorization")String bearerToken, @RequestParam Long gifId) {
        String token = bearerToken.substring(7);
        GifsBoardList gifsBoardList = boardService.getGifsBoard(token, gifId);
        return ResponseEntity.ok(gifsBoardList);
    }

    //-----------------------------------사용자의 보드 불러오기----------------------------------

    @GetMapping(params = "username")
    public ResponseEntity<MembersBoardList> getMembersBoard(@RequestParam String username) {
        MembersBoardList membersBoardList = boardService.getMembersBoard(username);
        return ResponseEntity.ok(membersBoardList);
    }
    //---------------------------------------보드 상세 불러오기(짤들)---------------------------------------
    @GetMapping("/{boardId}")
    public ResponseEntity<GifList> getGifs(@PathVariable Long boardId) {
        GifList gifList = boardService.getGifs(boardId);
        return ResponseEntity.ok(gifList);
    }

    //-----------------------------------짤을 보드에 추가 및 삭제(체크박스로)--------------------------------
    @PutMapping("/gif/{gifId}")
    public ResponseEntity updateBoard(@RequestHeader(value = "Authorization")String bearerToken, @PathVariable Long gifId, @RequestBody GifsBoardList gifsBoardList) {
        String token = bearerToken.substring(7);
        boardService.updateBoard(token, gifId, gifsBoardList);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //---------------------------------보드에서 짤 삭제--------------------------------
    @DeleteMapping("/{boardId}")
    public ResponseEntity deleteGifFromBoard(@PathVariable Long boardId, @RequestBody DeleteList gifList) {
        // 토큰은 SecuityConfig에서 처리
        boardService.deleteGifFromBoard(boardId, gifList.getGifList());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
