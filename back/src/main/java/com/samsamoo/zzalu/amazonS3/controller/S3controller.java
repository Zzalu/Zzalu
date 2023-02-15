package com.samsamoo.zzalu.amazonS3.controller;

import com.samsamoo.zzalu.TitleHakwon.entity.TitleHakwon;
import com.samsamoo.zzalu.TitleHakwon.enums.TitleHakwonState;
import com.samsamoo.zzalu.advice.ConflictException;
import com.samsamoo.zzalu.amazonS3.dto.S3request;
import com.samsamoo.zzalu.amazonS3.upLoader.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/s3")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class S3controller {
    private String DIRNAME ="BasicImage";
    private final S3Uploader s3Uploader;
    @PostMapping("/upload")
    public ResponseEntity upload(@ModelAttribute S3request s3request) throws IOException {
        String url =  s3Uploader.upload(s3request.getData(), DIRNAME);
        return new ResponseEntity<>(Map.of("urlPath", url), HttpStatus.CREATED);
    }
}
