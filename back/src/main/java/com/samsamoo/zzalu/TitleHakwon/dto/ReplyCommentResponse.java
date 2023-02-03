package com.samsamoo.zzalu.TitleHakwon.dto;

import com.samsamoo.zzalu.TitleHakwon.entity.ReplyComment;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ReplyCommentResponse {

    /** 댓글 **/
    private Long replyCommentId;

    /** 내용 **/
    private String content;

    /** 작성자 아이디 **/
    private String username;

    /** 작성자 닉네임 **/
    private String nickname;

    /** 대댓글 작성 시간 **/
    private String createdTime;
    /** 수정 여부 **/
    private boolean isUpdated;


    public ReplyCommentResponse(ReplyComment replyComment) {
        this.replyCommentId = replyComment.getId();
        this.content = replyComment.getContent();
        this.username = replyComment.getMember().getUsername();
        this.nickname = replyComment.getMember().getNickname();
        this.createdTime = replyComment.getCreatedDate();
        this.isUpdated = replyComment.isUpdated();
    }


    /** 응답 Dto List로 변환 **/
    public static List<ReplyCommentResponse> convertReplyCommentToDtoList(List<ReplyComment> replyCommentList) {

        List<ReplyCommentResponse> replyCommentResponseList = new ArrayList<>();

        for (ReplyComment replyComment : replyCommentList) {
            replyCommentResponseList.add(new ReplyCommentResponse(replyComment));
        }

        return replyCommentResponseList;
    }

}
