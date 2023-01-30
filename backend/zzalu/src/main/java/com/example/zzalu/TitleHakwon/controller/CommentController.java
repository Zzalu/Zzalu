package com.example.zzalu.TitleHakwon.controller;

import com.example.zzalu.TitleHakwon.dto.CommentResponse;
import com.example.zzalu.TitleHakwon.dto.ReplyCommentRequest;
import com.example.zzalu.TitleHakwon.dto.ReplyCommentResponse;
import com.example.zzalu.TitleHakwon.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.zzalu.TitleHakwon.dto.CommentRequest;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coment")
public class CommentController {

    private final CommentService commentService;

    /**
     * [CREATE]
     * 댓글 저장하기
     */
    @PostMapping
    public ResponseEntity<CommentResponse> addComent(@RequestBody CommentRequest requestComent){

        return ResponseEntity.status(HttpStatus.OK).body(commentService.addComment(requestComent));

    }

    /**
     * [CREATE]
     * 대댓글 저장하기
     */

    @PostMapping(value = "/reply")
    public ResponseEntity<ReplyCommentResponse> addReplyComent(@RequestBody ReplyCommentRequest replyCommentRequest){

        return ResponseEntity.status(HttpStatus.OK).body(commentService.addReplyComment(replyCommentRequest));

    }

    /**
     * [GET]
     * 댓글 가져오기
     * Cursor 기반 페이징
     */

    @GetMapping()
    public  ResponseEntity<List<CommentResponse>> getCommentList (@RequestParam Long lastCommentId, @RequestParam Long titleHakwonId ,@RequestParam int size){

        List<CommentResponse> commentResponseList = commentService.getCommentList(lastCommentId,titleHakwonId,size);
        return new ResponseEntity<>(commentResponseList,HttpStatus.OK);
    }

    /**
     * [GET]
     * 대댓글 가져오기
     * Cursor 기반 페이징
     */

    @GetMapping()
    public  ResponseEntity<List<ReplyCommentResponse>> getReplyCommentList (@RequestParam Long lastCommentId, @RequestParam Long parentCommentId ,@RequestParam int size){

        List<ReplyCommentResponse> replyCommentResponseList = commentService.getReplyCommentList(lastCommentId,parentCommentId,size);
        return new ResponseEntity<>(replyCommentResponseList,HttpStatus.OK);
    }


    /**
     * [UPDATE]
     * 댓글 수정
     */


    /**
     * [UPDATE]
     * 대댓글  수정
     */


    /**
     * [DELETE]
     * 댓글 삭제
     */


    /**
     * [DELETE]
     * 대댓글  삭제
     */
    @DeleteMapping
    public  ResponseEntity<String> deleteComment(Long commentId){


        return null;
    }

    @DeleteMapping
    public  ResponseEntity<String> deleteReplyComment(Long replyCommentId){
        return null;
    }

}
