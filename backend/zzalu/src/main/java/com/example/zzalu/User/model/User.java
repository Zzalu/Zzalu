package com.example.zzalu.User.model;


import com.example.zzalu.TitleHakwon.model.TitleHakwonComent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @Column(name="USER_ID")
    String id;

    String email;
    String password;

    @OneToMany (mappedBy = "user")
    private List<TitleHakwonComent> titleHakwonComents = new ArrayList<>();

}
