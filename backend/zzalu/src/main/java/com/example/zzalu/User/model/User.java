package com.example.zzalu.User.model;


import com.example.zzalu.TitleHakwon.model.TitleHakwonComent;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class User implements Serializable {

    @Id
    @Column(name="USER_ID")
    String id;

    String email;
    String password;

    @OneToMany (mappedBy = "user" ,cascade = CascadeType.ALL)
    private List<TitleHakwonComent> titleHakwonComents = new ArrayList<>();

}
