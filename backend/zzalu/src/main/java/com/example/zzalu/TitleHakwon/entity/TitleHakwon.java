package com.example.zzalu.TitleHakwon.entity;


import com.example.zzalu.TitleHakwon.entity.Coment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity

@Getter
@Setter


/**
 *
 * 게시판 (1)
 */
public class TitleHakwon implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TITLEHAKWON_ID")
    private Long id;

    @Column(length = 10000)
    private String zzulUrl;


    @OneToMany (mappedBy = "titleHakwon",cascade = CascadeType.ALL)
    private List<Coment> coments = new ArrayList<>();



}
