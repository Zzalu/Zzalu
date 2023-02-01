package com.example.zzalu.TitleHakwon.service;


import com.example.zzalu.TitleHakwon.dto.CommentResponse;
import com.example.zzalu.TitleHakwon.dto.ReplyCommentRequest;
import com.example.zzalu.TitleHakwon.dto.ReplyCommentResponse;
import com.example.zzalu.TitleHakwon.entity.Comment;
import com.example.zzalu.TitleHakwon.entity.CommentLike;
import com.example.zzalu.TitleHakwon.entity.ReplyComment;
import com.example.zzalu.TitleHakwon.repository.CommentLikeRepository;
import com.example.zzalu.TitleHakwon.repository.CommentRepository;
import com.example.zzalu.TitleHakwon.repository.ReplyCommentRepository;
import com.example.zzalu.TitleHakwon.repository.TitleHackwonRepository;
import com.example.zzalu.User.model.Member;
import com.example.zzalu.User.repository.MemberRepository;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import com.example.zzalu.TitleHakwon.dto.CommentRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor  //얘가 자동으로 생성자 주입해줌
@Repository
public class CommentService {

    private final CommentRepository commentRepository;
    private final ReplyCommentRepository replyCommentRepository;
    private final MemberRepository memberRepository;
    private final TitleHackwonRepository titleHackwonRepository;

    private final CommentLikeRepository commentLikeRepository;

    /**
     * 댓글 작성
     */
    public CommentResponse addComment (CommentRequest requestComment){

        Comment comment = Comment.builder()
                .member(memberRepository.findMemberByMemberId(requestComment.getMemberId()))
                .titleHakwon(titleHackwonRepository.findTitleHakwonById(requestComment.getTitleHakwonId()))
                .cotent(requestComment.getContent())
                .replyCommentList(new ArrayList<>())
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
                .parentComment(commentRepository.findById(replyCommentRequest.getParentCommentId()).get())
                .build();



      replyCommentRepository.save(replyComment);

        return ReplyCommentResponse.convertReplyCommentToDto(replyComment);


    }

    /**
     * 댓글 가져오기
     * 무한 스크롤 / 커서 기반 페이지 네이션
     */

    public List<CommentResponse> getCommentList (Long lastCommentId, Long titleHakwonId, int size){
        
        System.out.println(lastCommentId+"마지막아이디"+" "+size+"크기");

        Page<Comment> comments = fetchCommentPages(lastCommentId,titleHakwonId, size);

        System.out.println(comments.getContent());


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
    public void updateComment (Long id, CommentRequest commentRequest){

       Optional<Comment> comment = commentRepository.findById(id);


        //수정하고자 하는 댓글이 존재할때만 수정한다.
        if(comment!=null){

            System.out.println("@@");
            if(!StringUtils.isEmpty(commentRequest.getContent()) && !StringUtils.isEmpty(commentRequest.getMemberId())){
                comment.get().setCotent(commentRequest.getContent());
            }
            System.out.println("수정 가능");
            commentRepository.save(comment.get());
        }

    }


    /**
     * 대댓글 수정
     */

  public void updateReplyComment (Long id  , ReplyCommentRequest replyCommentRequest){

      ReplyComment replyComment = replyCommentRepository.findById(id);

      if(replyComment!=null){
          if(!StringUtils.isEmpty(replyCommentRequest.getContent())){
              replyComment.setCotent(replyCommentRequest.getContent());
          }
          replyCommentRepository.save(replyComment);
      }



  }

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
    @Transactional
    public void  deleteReplyCommnete(Long id){
        replyCommentRepository.deleteById(id);

    }


    /**
     * 댓글에 좋아요 누르기
     *
     * 1. 댓글 좋아요 기록에 추가
     * 2. 댓글 좋아요 +1
     */
    public void clickCommentLikes(Long commentId , String memberId){

        //존재하지 않은 댓글이였다면?
        Optional<Comment> comment = commentRepository.findById(commentId);
        Optional<Member> member = memberRepository.findById(memberId);

        if(!comment.isPresent()){
            return;
        }
        if(!member.isPresent()){
                return;
        }
        //존재하지 않는 멤버였다면?

        CommentLike commentLike = CommentLike.builder()
                .member(member.get())
                .comment(comment.get()).build();
        //좋아요을 눌렀으면 좋아요기록 테이블에 저장하고

        commentLikeRepository.save(commentLike);

        //해당 댓글의 좋아요 +1을 증가시킨다.
        comment.get().setLikeNum(comment.get().getLikeNum()+1);
        commentRepository.save(comment.get());

    }

    /**
     * 댓글에 좋아요 취소하기
     * 1.댓글 좋아요 기록에서 삭제
     * 2. 댓글 좋아요 -1
     */

    public void cancelCommentLikes(Long id){

    }


    /**
     * 댓글 좋아요 기록이 존재하는지
     */

    public  boolean existCommentLike(Long commentId ,String memberId ){

        return commentLikeRepository.existsByComment_IdAndMember_MemberId(commentId,memberId);
    }

}
