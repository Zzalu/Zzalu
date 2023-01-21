package com.example.zzalu.TitleHakwon;


import com.example.zzalu.amazonS3.upLoader.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class TitleHakwonController {



    private String DIRNAME ="TitleHakwon";
    private final S3Uploader s3Uploader;


    //관리자가 제목학원을 등록합니다.

    //data로 넘어오는 MultipartFile을 S3Uploader로 전달
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {

        //지정된 bucket의 dir(DIRNAME)으로 업로드 하겠다
        return s3Uploader.upload(multipartFile, DIRNAME);
    }
}
