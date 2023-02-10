package com.samsamoo.zzalu.TitleHakwon.controller;


import com.samsamoo.zzalu.TitleHakwon.dto.CommentResponse;
import com.samsamoo.zzalu.TitleHakwon.dto.ReplyCommentResponse;
import com.samsamoo.zzalu.TitleHakwon.dto.TitleHakwonDetailResponse;
import com.samsamoo.zzalu.TitleHakwon.dto.TitleHakwonResponse;
import com.samsamoo.zzalu.TitleHakwon.enums.TitleHakwonState;
import com.samsamoo.zzalu.TitleHakwon.repository.TitleHackwonRepository;
import com.samsamoo.zzalu.TitleHakwon.entity.TitleHakwon;
import com.samsamoo.zzalu.TitleHakwon.service.CommentService;
import com.samsamoo.zzalu.TitleHakwon.service.TitleHakwonService;
import com.samsamoo.zzalu.amazonS3.upLoader.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/title-hakwon")
@CrossOrigin("*")
public class TitleHakwonController {

    private String DIRNAME ="TitleHakwon";
    private final S3Uploader s3Uploader;
    private  final TitleHackwonRepository titleHackwonDao;
    private final CommentService commentService;
    private final TitleHakwonService titleHakwonService;



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
            titleHakwon.setState(TitleHakwonState.NOT_OPEN);
            System.out.println("생성");
            System.out.println(TitleHakwonState.NOT_OPEN+"@@@@");
            System.out.println(TitleHakwonState.NOT_OPEN+"@@@@");
            titleHackwonDao.save(titleHakwon);

            return url;
        }


    }

    //제목 학원 등록하기
    // (will) 지금은 url을 직접 입력해야 하지만 , s3에 저장하고 url을 리턴받아 저장할 계획

    @PostMapping("/upload")
    public  ResponseEntity<String>   uploadTitleHakwon(@RequestParam ("zzalUrl") String url) throws IOException{

        ResponseEntity response = null;


        if(url==""){
            return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
        }else{
            //타이틀 학원을 등록한다.
            TitleHakwon titleHakwon = new TitleHakwon();
            titleHakwon.setZzulUrl(url);
            titleHakwon.setState(TitleHakwonState.NOT_OPEN);
            titleHackwonDao.save(titleHakwon);

            return new ResponseEntity<String>("sucess", HttpStatus.OK);

        }

    }

    /**
     * 오늘의 제목학원 정보 얻어오기
     */

    @GetMapping(value = "/{openDate}")
    public ResponseEntity<TitleHakwonDetailResponse> getTitlehakwonInfo(@PathVariable String openDate){

        return new ResponseEntity<>(titleHakwonService.getTitleHakwonInfo(openDate), HttpStatus.OK);
    }




    /**
     * [GET]
     * 댓글 가져오기
     * Cursor 기반 페이징
     */

    @GetMapping("/{titleHakwonId}/comments")
    public  ResponseEntity<List<CommentResponse>> getRecentCommentList (@RequestHeader( required = false, value = "Authorization") String bearerToken ,@PathVariable Long titleHakwonId, @RequestParam Long lastCid , @RequestParam int limit, @RequestParam String sort){



        String token;
        if(bearerToken==null){
            System.out.println("헤더값 없어요!!");
            token = null;
        }else{
             token = bearerToken.substring(7);
             System.out.println(token);
        }

        List<CommentResponse> commentResponseList;
        if(sort.equals("LATEST")){
            commentResponseList = commentService.getRecentCommentList(titleHakwonId,lastCid,limit, token);

            for(CommentResponse c : commentResponseList){
                System.out.println(c.getContent());
            }
            return new ResponseEntity<>(commentResponseList,HttpStatus.OK);
        } else if (sort.equals("CHRONOLOGICAL")) {
            commentResponseList = commentService.getPastCommentList(titleHakwonId,lastCid,limit, token);
            return new ResponseEntity<>(commentResponseList,HttpStatus.OK);
        }else {
            return null;
        }


    }

    /**
     * [GET]
     * 대댓글  최신순 가져오기
     * Cursor 기반 페이징
     */

    @GetMapping(value = "/comments/{parentId}/reply")
    public  ResponseEntity<List<ReplyCommentResponse>> getReplyCommentList (@PathVariable Long parentId, @RequestParam Long lastCid , @RequestParam int limit, @RequestParam String sort){



        //status code =200
        List<ReplyCommentResponse> replyCommentResponseList;
        if(sort.equals("LATEST")){
            replyCommentResponseList = commentService.getRecentReplyCommentList( lastCid ,  parentId ,   limit );
            return new ResponseEntity<>(replyCommentResponseList,HttpStatus.OK);
        }else if (sort.equals("CHRONOLOGICAL")){
            replyCommentResponseList = commentService.getPastReplyCommentList( lastCid ,  parentId ,   limit );
            return new ResponseEntity<>(replyCommentResponseList,HttpStatus.OK);
        }else {
            return null;
        }

    }



    /**
     * [GET]
     * 상위 좋아요 50개 댓글
     */
    @GetMapping("/{titleHakwonId}/comments/best")
    public  ResponseEntity<List<CommentResponse>> getBest50CommentList (@PathVariable Long titleHakwonId, @RequestParam int limit ,@RequestParam String sort , @RequestParam String username){


        //code 200
        if(sort.equals("POPULAR")){
            List<CommentResponse> commentResponseList = commentService.getBest50CommentList(limit,titleHakwonId, username);
            return new ResponseEntity<>(commentResponseList,HttpStatus.OK);
        }
        return null;
      /*  //로그인한 유저가 아닌 경우는
        Long titleHakwonId = Long.parseLong(map.get("titleHakwonId").toString());
        String username = map.get("username")==null ? null : map.get("username");
        //로그인한 유저가 있다면 상위 좋아요 50개 댓글을 불러올때 댓글 누른 기록을 확인..*/


       // return new ResponseEntity<>(commentResponseList,HttpStatus.OK);
    }

    /**
     * [GET]
     역대 제목학원 리스트 조회하기
     */

    @GetMapping()
    public ResponseEntity<List<TitleHakwonResponse>> getTitleHakwonList(){

        List<TitleHakwonResponse> titleHakwonResponseList = titleHakwonService.getTitleHakwonList();
        return new ResponseEntity<>(titleHakwonResponseList,HttpStatus.OK);
    }

}
