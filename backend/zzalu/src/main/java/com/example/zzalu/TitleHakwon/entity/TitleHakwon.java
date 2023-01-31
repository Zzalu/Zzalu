package com.example.zzalu.TitleHakwon.entity;


import com.example.zzalu.TitleHakwon.entity.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)

/**
 *
 * 게시판 (1)
 */
public class TitleHakwon  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TITLEHAKWON_ID")
    private Long id;

    @Column(length = 10000)
    private String zzulUrl;



    private String openDate;


    @PrePersist
    public void onPrePersist(){
        this.openDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }




    @OneToMany(fetch = FetchType.LAZY, mappedBy = "titleHakwon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();



}
