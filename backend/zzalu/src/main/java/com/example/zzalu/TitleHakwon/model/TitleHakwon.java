package com.example.zzalu.TitleHakwon.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TitleHakwon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TITLEHAKWON_ID")
    private Long id;

    @Column(length = 10000)
    private String zzulUrl;


    @OneToMany (mappedBy = "titleHakwon")
    private List<TitleHakwonComent> titleHakwonComents = new ArrayList<>();



}
