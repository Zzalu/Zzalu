package com.example.zzalu.TitleHakwon.model;

import com.example.zzalu.User.model.User;

import javax.persistence.*;

@Entity
public class TitleHakwonComent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TITLEHAKWON_COMENT_ID")
    private Long id;

    // N:1 양방향
    @ManyToOne
    @JoinColumn(name = "TITLEHAKWON_ID")
    private TitleHakwon titleHakwon;



    // N:1 양방향
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    private String commnet;
    private int likeNumber;
    private int rank;

}
