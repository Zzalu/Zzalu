package com.samsamoo.zzalu.TitleHakwon.entity;


import com.samsamoo.zzalu.TitleHakwon.enums.TitleHakwonState;
import com.samsamoo.zzalu.TitleHakwon.enums.converter.TitleHakwonConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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


    @Column(nullable = false, unique = true)
    private String openDate;



   @Convert(converter =  TitleHakwonConverter.class)
    private TitleHakwonState state ;


    @PrePersist
    public void onPrePersist(){
        this.openDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "titleHakwon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();



}
