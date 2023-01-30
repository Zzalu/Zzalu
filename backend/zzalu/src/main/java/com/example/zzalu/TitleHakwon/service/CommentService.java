package com.example.zzalu.TitleHakwon.service;


import com.example.zzalu.TitleHakwon.dto.CommentResponse;
import com.example.zzalu.TitleHakwon.dto.ReplyCommentRequest;
import com.example.zzalu.TitleHakwon.dto.ReplyCommentResponse;
import com.example.zzalu.TitleHakwon.entity.Comment;
import com.example.zzalu.TitleHakwon.entity.ReplyComment;
import com.example.zzalu.TitleHakwon.repository.CommentRepository;
import com.example.zzalu.TitleHakwon.repository.ReplyCommentRepository;
import com.example.zzalu.TitleHakwon.repository.TitleHackwonRepository;
import com.example.zzalu.User.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import com.example.zzalu.TitleHakwon.dto.CommentRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor  //얘가 자동으로 생성자 주입해줌
@Repository
public class CommentService {

    private final CommentRepository commentRepository;
    private final ReplyCommentRepository replyCommentRepository;
    private final MemberRepository memberRepository;
    private final TitleHackwonRepository titleHackwonRepository;

    /**
     * 댓글 작성
     */
    public CommentResponse addComment (CommentRequest requestComment){

        Comment comment = Comment.builder()
                .member(memberRepository.findMemberByMemberId(requestComment.getMemberId()))
                .titleHakwon(titleHackwonRepository.findTitleHakwonById(requestComment.getTitleHakwonId()))
                .cotent(requestComment.getContent())
                .build();


        commentRepository.save(comment);

        return CommentResponse.convertCommentToDto(comment);


    }

    /**
     * 대댓글 작성
     */
    public ReplyCommentResponse addReplyComment (ReplyCommentRequest replyCommentRequest){

        /**
         * 예외 처리 1 : 부모 댓글이 존재하지 않을경우  ( )
         */

        ReplyComment replyComment = ReplyComment.builder()
                .member(memberRepository.findMemberByMemberId(replyCommentRequest.getMemberId()))
                .cotent(replyCommentRequest.getContent())
                .parentComment(commentRepository.findById(replyCommentRequest.getParentComentId()))
                .build();


      replyCommentRepository.save(replyComment);

        return ReplyCommentResponse.convertReplyCommentToDto(replyComment);


    }

    /**
     * 댓글 가져오기
     * 무한 스크롤 / 커서 기반 페이지 네이션
     */

    public List<CommentResponse> getCommentList (Long lastCommentId, Long titleHakwonId, int size){

        Page<Comment> comments = fetchCommentPages(lastCommentId,titleHakwonId, size);

        return CommentResponse.convertCommentToDtoList(comments.getContent());
    }

    private Page<Comment> fetchCommentPages(Long lastCommentId, Long titleHakwonId ,int size) {
        PageRequest pageRequest = PageRequest.of(0, size); // 페이지네이션을 위한 PageRequest, 페이지는 0으로 고정한다.
        return commentRepository.findByIdLessThanAndTitleHakwonIdOrderByIdDesc(lastCommentId,titleHakwonId , pageRequest); // JPA 쿼리 메소드
    }

    /**
     * 대댓글 가져오기
     * 커서 기반 페이지 네이션
     */

    public List<ReplyCommentResponse> getReplyCommentList (Long lastReplyCommentId, Long parentCommentId, int size){

        Page<ReplyComment> replyComments = fetchReplyCommentPages(lastReplyCommentId,parentCommentId, size);

        return ReplyCommentResponse.convertReplyCommentToDtoList(replyComments.getContent());
    }


    public Page<ReplyComment> fetchReplyCommentPages (Long lastReplyCommentId,Long parentCommentId, int size){
        PageRequest pageRequest = PageRequest.of(0, size);
        return  replyCommentRepository.findByIdLessThanAndParentCommentIdOrderByIdDesc(lastReplyCommentId,parentCommentId,pageRequest);
    }

    /**
     * 댓글 수정
     * 이 댓글을 작성한 사용자인지 아닌지 판단하게끔 백에서 해줘야하나?
     */


    /**
     * 대댓글 수정
     */



    /**
     * 댓글 삭제
     */
    @Transactional
    public int deleteComment (Long id){
         commentRepository.deleteById(id);
        return 1;

    }


    /**
     * 대댓글 삭제
     */

    public int  deleteReplyCommnete(Long id){
        ReplyComment comment = replyCommentRepository.deleteById(id);
        return 1;
    }

}
