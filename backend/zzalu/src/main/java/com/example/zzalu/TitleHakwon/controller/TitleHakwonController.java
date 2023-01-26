package com.example.zzalu.TitleHakwon.controller;


import com.example.zzalu.TitleHakwon.repository.ComentRepository;
import com.example.zzalu.TitleHakwon.repository.TitleHackwonRepository;
import com.example.zzalu.TitleHakwon.entity.Coment;
import com.example.zzalu.TitleHakwon.entity.TitleHakwon;
import com.example.zzalu.amazonS3.upLoader.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/TitleHakwon")
public class TitleHakwonController {

    private String DIRNAME ="TitleHakwon";
    private final S3Uploader s3Uploader;

    @Autowired
    TitleHackwonRepository titleHackwonDao;
    @Autowired
    ComentRepository comentDao;


    //관리자가 제목학원을 등록합니다.

/*    //data로 넘어오는 MultipartFile을 S3Uploader로 전달
    @PostMapping("/uploadToAmazon")
    public ResponseEntity<String>  upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {

        String url =  s3Uploader.upload(multipartFile, DIRNAME);
        if(url==""){
            return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
        }else{
            //타이틀 학원을 등록한다.
            TitleHakwon titleHakwon = new TitleHakwon();
            titleHakwon.setZzulUrl(url);
            titleHackwonDao.save(titleHakwon);

            return new ResponseEntity<String>("sucess", HttpStatus.OK);
        }


    }*/

        //data로 넘어오는 MultipartFile을 S3Uploader로 전달
    @PostMapping("/uploadToAmazon")
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {

        String url =  s3Uploader.upload(multipartFile, DIRNAME);
        if(url==""){
            //return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
            return "r";
        }else{
            //타이틀 학원을 등록한다.
            TitleHakwon titleHakwon = new TitleHakwon();
            titleHakwon.setZzulUrl(url);
            titleHackwonDao.save(titleHakwon);

           return url;
        }


    }

    //제목 학원 등록하기
    // (will) 지금은 url을 직접 입력해야 하지만 , s3에 저장하고 url을 리턴받아 저장할 계획

    @PostMapping("/upload")
    public  ResponseEntity<String>   uploadTitleHakwon(@RequestParam("zzalUrl") String url) throws IOException{

        ResponseEntity response = null;


        if(url==""){
            return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
        }else{
            //타이틀 학원을 등록한다.
            TitleHakwon titleHakwon = new TitleHakwon();
            titleHakwon.setZzulUrl(url);
            titleHackwonDao.save(titleHakwon);

            return new ResponseEntity<String>("sucess", HttpStatus.OK);

        }

    }

    @PostMapping("/uploadComent")
    public  ResponseEntity<String>   uploadComent(@RequestBody Coment titleHakwonComent) throws IOException{



        //1.현재 활성중인 제목학원의 객체를 가져온다.

            //TitleHakwon titleHakwon = titleHackwonDao.findTitleHakwonById(titleHakwonComent.getTitleHakwon().getId());


          //  titleHakwon.getTitleHakwonComents().add(titleHakwonComent);




          // titleHackwonDao.save(titleHakwon); //게시판 Dao
           comentDao.save(titleHakwonComent);
           //@
            return new ResponseEntity<String>("sucess", HttpStatus.OK);

        }






}
