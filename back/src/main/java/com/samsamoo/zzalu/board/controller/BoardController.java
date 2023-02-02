package com.samsamoo.zzalu.board.controller;

import com.samsamoo.zzalu.board.dto.CreateBoardRequest;
import com.samsamoo.zzalu.board.dto.CreateBoardResposne;
import com.samsamoo.zzalu.board.repo.BoardRepository;
import com.samsamoo.zzalu.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    //--------------------------------------보드 생성-----------------------------------------
    @PostMapping
    public ResponseEntity<CreateBoardResposne> createBoard(@RequestHeader(value = "Authorization")String bearerToken, @RequestBody Map<String, String> request) {
        String token = bearerToken.substring(7);
        CreateBoardResposne response = boardService.createBoard(token, request.get("boardName"));
        return ResponseEntity.created(URI.create("/boards/" + response.getId())).body(response);
    }

}
