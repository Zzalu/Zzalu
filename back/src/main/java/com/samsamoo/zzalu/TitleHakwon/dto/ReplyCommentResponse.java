package com.samsamoo.zzalu.TitleHakwon.dto;

import com.samsamoo.zzalu.TitleHakwon.entity.DeleteCommentStatus;
import com.samsamoo.zzalu.TitleHakwon.entity.ReplyComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ReplyCommentResponse {

    /** 댓글 **/
    private Long id;

    /** 내용 **/
    private String content;

    /** 작성자 아이디 **/
    private String memberId;

    /** 작성자 비밀 번호 **/
    private String nickname;

    /** 좋아요 개수 **/
    private int likeNumber;

    /** 댓글 작성 시간 **/
    private LocalDateTime createTime;


    public ReplyCommentResponse(Long id, String content, String memberId, String nickname, int likeNumber,LocalDateTime createTime) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
        this.nickname = nickname;
        this.likeNumber = likeNumber;
        this.createTime = createTime;
    }

    public static ReplyCommentResponse convertReplyCommentToDto(ReplyComment replyComment) {
        //삭제된 댓글이라면 삭제 된 댓글이라고 알려준다.
        return replyComment.getIsDeleted() == DeleteCommentStatus.Y ?
                new ReplyCommentResponse(replyComment.getId(), "삭제된 댓글입니다.", null, null,0,null) :
                new ReplyCommentResponse(replyComment.getId(), replyComment.getCotent(), replyComment.getMember().getUsername(), replyComment.getMember().getNickname(), replyComment.getLikeNum(),replyComment.getCreatedDate());
    }

    public static List<ReplyCommentResponse>  convertReplyCommentToDtoList(List<ReplyComment> replyCommentList){
        List<ReplyCommentResponse> replyCommentResponseList = new ArrayList<>();
        for(ReplyComment replyComment : replyCommentList){
            replyCommentResponseList.add(convertReplyCommentToDto(replyComment));
        }

        return replyCommentResponseList;
    }
}
