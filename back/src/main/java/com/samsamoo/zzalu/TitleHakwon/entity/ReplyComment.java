package com.samsamoo.zzalu.TitleHakwon.entity;

import com.samsamoo.zzalu.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 대댓글 Entity
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

@Entity
public class ReplyComment {

    /** 대댓글 고유 아이디 **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMENT_ID")
    Long id;

    /** 대댓글 내용 **/
    @Column(nullable = false)
    @Lob //가변길의를 갖는 큰 데이터를 저장하는데 사용하는 데이터형이다
    String cotent;

    /** 작성자 아이디 **/
    // N:1 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    /**
     * 댓글 아이디
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    //좋아요 개수
    @ColumnDefault("0")
    int likeNum;


    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @Enumerated(value = EnumType.STRING)
    private DeleteCommentStatus isDeleted;


}
