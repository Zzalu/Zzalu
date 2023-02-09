package com.samsamoo.zzalu.tempGif.controller;

import com.samsamoo.zzalu.member.dto.UpdateMemberRequest;
import com.samsamoo.zzalu.tempGif.dto.TempGifRequest;
import com.samsamoo.zzalu.tempGif.entity.TempGif;
import com.samsamoo.zzalu.tempGif.service.TempGifService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/temp")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class TempGifController {
    private final TempGifService tempGifService;

    //--------------------------------모든 임시 게시물 조회---------------------------------
    //--------------------------------단일 임시 게시물 조회---------------------------------
    //--------------------------------임시 게시물 생성 요청---------------------------------
    @PostMapping
    public ResponseEntity createGifReq(@RequestHeader(value = "Authorization") String bearerToken, @ModelAttribute TempGifRequest request) {
        return null;
    }
    //--------------------------------원본 게시물 수정 요청---------------------------------
    //-----------------------------temp gif entity 삭제 요청------------------------------




}
