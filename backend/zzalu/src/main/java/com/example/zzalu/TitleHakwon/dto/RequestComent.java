package com.example.zzalu.TitleHakwon.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RequestComent {

    //댓글 달때 아이디는 안넘겨줘도됨 (auto_increment)
    /** 사용자 ID **/
    private String memberId;
    /** 댓글 내용 **/
    private String content;

    private Long titleHakwonId;

    int parentComentId;

    int comentOrder;

    Long groupNum;



}
