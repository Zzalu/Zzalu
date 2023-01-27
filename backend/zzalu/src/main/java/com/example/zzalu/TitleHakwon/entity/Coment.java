package com.example.zzalu.TitleHakwon.entity;


import com.example.zzalu.User.model.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


/**
 * @Author 홍금비
 * 댓글 Entity
**/

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)


@Entity

public class Coment {

    //대댓글 고유 아이디 (auto)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMENT_ID")
    Long id;
    //작성자 아이디
    // N:1 양방향

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
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

    @Column( name = "parent_coment_id",nullable = true)
    int parentComentId;

    //댓글 순서
    int comentOrder;

    /*
     댓글 인덱스가 들어감
     하지만 대댓글은 자신의 인덱스 대신 부모의 댓글 인덱스가 저장
     대댓글이 특정 댓글에 종속되어있다는 것을 표시하기 위한 수단
     */


    //좋아요 개수
    @ColumnDefault("0")
    int likeNum;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Builder
    public Coment(Member member, TitleHakwon titleHakwon, String cotent, int parentComentId, int comentOrder, int likeNum) {
        this.member = member;
        this.titleHakwon = titleHakwon;
        this.cotent = cotent;
        this.parentComentId = parentComentId;
        this.comentOrder = comentOrder;
        this.createdDate = new Date();
        this.likeNum = likeNum;
    }


}
