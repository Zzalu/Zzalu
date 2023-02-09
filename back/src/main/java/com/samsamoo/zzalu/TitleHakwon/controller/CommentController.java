package com.samsamoo.zzalu.TitleHakwon.controller;

import com.samsamoo.zzalu.AwardRecord.repository.AwardRecordRepository;
import com.samsamoo.zzalu.AwardRecord.service.AwardRecordService;
import com.samsamoo.zzalu.TitleHakwon.dto.*;
import com.samsamoo.zzalu.TitleHakwon.repository.RedisCommentRepository;
import com.samsamoo.zzalu.TitleHakwon.service.CommentService;
import com.samsamoo.zzalu.redis.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
public class CommentController {


    private final RedisPublisher redisPublisher;
    private final CommentService commentService;

    private final RedisCommentRepository redisCommentRepository;

    private final AwardRecordService awardRecordService;



    /**
     * [POST]
     * 댓글 저장하기
     */
    @PostMapping()
    public ResponseEntity<CommentResponse> addComent(@RequestBody CommentRequest requestComent){

        // 좋아요가 반영이 되었다면 redis 를 통해 pub한다
        //채팅서버가 여러개일 경우 websokect으로는 모든 클라이언트에게 똑같이 반영할 수 없기 때문



        CommentResponse commentResponse = commentService.addComment(requestComent);
        System.out.println("@@redis");
        redisPublisher.publishTitleHakwon( redisCommentRepository.getTopic("comments"),commentResponse);
        //201리턴
        return ResponseEntity.status(HttpStatus.OK).body(commentResponse);

    }

    /**
     * [POST]
     * 대댓글 저장하기
     */

    @PostMapping(value = "/reply")
    public ResponseEntity<ReplyCommentResponse> addReplyComent(@RequestBody ReplyCommentRequest replyCommentRequest){
//201리턴
        return ResponseEntity.status(HttpStatus.OK).body(commentService.addReplyComment(replyCommentRequest));

    }




    /**
     * [UPDATE]
     * 댓글 수정
     */
    @PatchMapping("/comment")
    public ResponseEntity<String> updateComment (@RequestBody UpdateCommentRequest commentRequest){
        commentService.updateComment(commentRequest);
        return new ResponseEntity<>("댓글 변경완료",HttpStatus.OK);
    }


    /**
     * [UPDATE]
     * 대댓글  수정
     */

    @PatchMapping(value = "/reply-comment")
    public  ResponseEntity<String> updateReplyComent (@RequestBody UpdateCommentRequest commentRequest){
        commentService.updateReplyComment(commentRequest);
        return new ResponseEntity<>("대댓글 변경완료",HttpStatus.OK);
    }


    /**
     * [DELETE]
     * 댓글 삭제
     */


    @DeleteMapping("/{commentId}")
    public  ResponseEntity<String> deleteComment(@PathVariable Long commentId){

        //delete status code =204
        System.out.println(commentId);

        if(commentService.deleteComment(commentId)==1){

            return new ResponseEntity<>("삭제완료",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("삭제 불가",HttpStatus.NO_CONTENT);
        }


    }


    /**
     * [DELETE]
     * 대댓글  삭제
     */

    @DeleteMapping(value = "/reply/{replyCommentId}")
    public  ResponseEntity deleteReplyComment(@PathVariable Long replyCommentId){


       commentService.deleteReplyCommnete(replyCommentId);

       System.out.println("[DELETE] 대댓글 삭제 완료");
        return new ResponseEntity(HttpStatus.OK);

    }


    /**
     * [POST]
     * 댓글 좋아요 하기
     */

    @PostMapping("/{commentId}/likes")
    public ResponseEntity<String> clickCommentLikes (@PathVariable Long commentId,  @RequestParam String username){
        //201
        //좋아요를 할껀데 기존에 누른 기록이 있었으면 안됨
        if(commentService.existCommentLike(commentId,username)){
            return new ResponseEntity<>("이미존재함",HttpStatus.FORBIDDEN);
        }else{
            //그렇지 않은 경우는 좋아요 가능
            //없는회원이면,,,, -> 예외처리하자  좋아요 완료로뜬다.
           LikeResponse likeResponse = commentService.clickCommentLikes(commentId,username);
           System.out.println(likeResponse+"@@@@");

   /*         // 좋아요가 반영이 되었다면 redis 를 통해 pub한다
            //채팅서버가 여러개일 경우 websokect으로는 모든 클라이언트에게 똑같이 반영할 수 없기 때문
            redisPublisher.publish("title-Hakwon",);*/

            /** 좋아요가 다 눌렷으면 redis pub 좋아요 +1 을 한다 */

            redisPublisher.pubLikes(redisCommentRepository.getTopic("likes"),likeResponse);


            return new ResponseEntity<>("좋아요 완료 ",HttpStatus.OK);
        }
    }


    /**
     * [DELETE]
     * 댓글 좋아요 취소하기
     */
    @DeleteMapping("{commentId}/likes")
    public ResponseEntity<String> cancelCommentLikes (@PathVariable Long commentId,  @RequestParam String username){


        //200
        if(!commentService.existCommentLike(commentId,username)){
            return new ResponseEntity<>("좋아요를 누른 기록이 없습니다.",HttpStatus.FORBIDDEN);
        }else{
            //그렇지 않은 경우는 좋아요 가능


            LikeResponse likeResponse = commentService.cancelCommentLikes(commentId,username);
            redisPublisher.pubLikes(redisCommentRepository.getTopic("likes"),likeResponse);

            return new ResponseEntity<>("좋아요 취소 완료 ",HttpStatus.OK);
        }
    }


}