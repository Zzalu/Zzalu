package com.samsamoo.zzalu.TitleHakwon.dto;


import com.samsamoo.zzalu.TitleHakwon.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;



@NoArgsConstructor
@Getter
public class CommentResponse {

    /** 댓글 ID **/
    private Long id;
    /** 내용 **/
    private String content;

    /** 사용자 아이디 **/
    private String username;
    /** 사용자 닉네임 **/
    private String nickname;
    /** 대댓글 개수 **/
    private int replyCommentsSize;
    /** 좋아요 개수 **/
    private int likeNumber;

    /** 작성 시간 **/
    private String createdTime;

    /** 수정 여부 **/
    private boolean isUpdated;

    /** 좋아요 기록 **/
    private boolean isPressed =false;



    /** 응답 Dto로 변환 **/
    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getMember().getUsername();
        this.nickname = comment.getMember().getNickname();
        this.replyCommentsSize = comment.getReplyCommentList().size();
        this.likeNumber = comment.getLikeNum();
        this.createdTime =  comment.getCreatedDate();
        this.isUpdated = comment.isUpdated();
    }

    /** 응답 Dto List로 변환 **/
    public static List<CommentResponse> convertCommentToDtoList(List<Comment> commentList) {

        List<CommentResponse> commentResponseList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentResponseList.add(new CommentResponse(comment));
        }

        return commentResponseList;
    }

    public void updateIsPressed(){
        this.isPressed = true;
    }
}
