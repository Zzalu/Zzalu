package com.example.zzalu.TitleHakwon.entity;


import com.example.zzalu.User.model.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * @Author 홍금비
 * 댓글 Entity
**/

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@SequenceGenerator(
        name = "USER_SEQ_GENERATOR"
        , sequenceName = "USER_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
@Entity

public class Coment {

    //대댓글 고유 아이디 (auto)
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "USER_SEQ_GENERATOR"
    )
    @Column(name="COMENT_ID")
    Long id;
    //작성자 아이디
    // N:1 양방향

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Member member;

    //제목학원 id
    //N:1 양방향
    @ManyToOne
    @JoinColumn(name = "TITLEHAKWON_ID")
    private TitleHakwon titleHakwon;

    //대댓글 내용
    String cotent;

    //제목학원 id

    //계층  0은부모 자식은 1
    int classNum;

    //댓글 순서
    int comentOrder;

    /*
     댓글 인덱스가 들어감
     하지만 대댓글은 자신의 인덱스 대신 부모의 댓글 인덱스가 저장
     대댓글이 특정 댓글에 종속되어있다는 것을 표시하기 위한 수단
     */

    Long groupNum;

    //좋아요 개수
    @ColumnDefault("0")
    int likeNum;


    public void setGroupNum(Long groupNum) {
        if(groupNum==0){ //그룹넘버가 없는경우 (댓글인 경우 자기 자신의 값 저장)
            this.groupNum=id;
        }
        this.groupNum = groupNum;
    }
    @Builder

    public Coment(Member member, TitleHakwon titleHakwon, String cotent, int classNum, int comentOrder, Long groupNum, int likeNum) {
        this.member = member;
        this.titleHakwon = titleHakwon;
        this.cotent = cotent;
        this.classNum = classNum;
        this.comentOrder = comentOrder;
        this.groupNum = groupNum;
        this.likeNum = likeNum;
    }
}
