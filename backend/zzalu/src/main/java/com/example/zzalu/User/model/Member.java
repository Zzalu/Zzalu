package com.example.zzalu.User.model;


import com.example.zzalu.TitleHakwon.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Member  {

    @Id
    @Column(name="MEMBER_ID")
    String memberId;

    String nickName;

    String email;


    String password;

    @OneToMany (mappedBy = "member" ,cascade = CascadeType.ALL)
    private List<Comment> coments = new ArrayList<>();


}
