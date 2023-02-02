package com.samsamoo.zzalu.User.model;


import com.samsamoo.zzalu.TitleHakwon.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
