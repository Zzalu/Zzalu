package com.samsamoo.zzalu.gifs.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TempGif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TEMP_GIF_ID")
    private Long id;
    @Column(nullable = false, unique = true)
    private String gifPath;
    @Column(nullable = false)
    private String tags;
    private String importDatetime; // api에서 가져온 것들
    private String relationsVideo;
    private String description;
    private String writerUsername;
    @Builder.Default
    private Long permittedCount = 0l;
    private Enum requestType;
}
