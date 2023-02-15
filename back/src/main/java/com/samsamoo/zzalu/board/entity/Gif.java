package com.samsamoo.zzalu.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gif {
    @Id
    @Column(name = "GIF_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
