package com.samsamoo.zzalu.TitleHakwon.dto;


import com.samsamoo.zzalu.TitleHakwon.entity.Comment;
import com.samsamoo.zzalu.TitleHakwon.entity.DeleteCommentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class CommentResponse {

    /** 댓글 **/
    private Long id;

    /** 내용 **/
    private String content;

    private String memberId;
    private String nickname;

    private int replyCommentsSize;

    private int likeNumber;


    public CommentResponse(Long id, String content, String memberId, String nickname,int replyCommentsSize, int likeNumber) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
        this.nickname = nickname;
        this.replyCommentsSize = replyCommentsSize;
        this.likeNumber = likeNumber;
    }
    public static CommentResponse convertCommentToDto(Comment comment) {
        if(comment.getReplyCommentList()==null){
            System.out.println("리스트없음");
        }
        //삭제된 댓글이라면 삭제 된 댓글이라고 알려준다.
        return comment.getIsDeleted() == DeleteCommentStatus.Y ?
                new CommentResponse(comment.getId(), "삭제된 댓글입니다.", null, null,0,0) :
                new CommentResponse(comment.getId(), comment.getCotent(), comment.getMember().getMemberId(), comment.getMember().getNickName(),comment.getReplyCommentList().size(),comment.getLikeNum());
    }

    public static List<CommentResponse> convertCommentToDtoList(List<Comment> commentList) {

        List<CommentResponse> commentResponseList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentResponseList.add(convertCommentToDto(comment));
        }


        return commentResponseList;
    }
}
