package com.samsamoo.zzalu.gifs.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity(name="com.samsamoo.zzalu.gifs.entity.GiphyGifs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GiphyGifs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GIF_ID")
    private Long id;

    @Column(name="GIF_PATH", nullable = false, unique = true)
    private String gifPath;

    @Column(name="USE_COUNT", nullable = false)
    @Builder.Default
    @NotNull
    private Long useCount = 0L;

    @Column(name="DOWNLOAD_COUNT", nullable = false)
    @Builder.Default
    @NotNull
    private Long downloadCount = 0L;

    @Column(name="LIKE_COUNT", nullable = false)
    @Builder.Default
    @NotNull
    private Long likeCount = 0L;

    @Column(name="TAGS")
    private String tags;

    @Column(name="SORUCES")
    private String sources;

    @Column(name="SORUCES_POST_URL")
    private String sourcesPostUrl;

    @Column(name="SORUCES_TLD")
    private String sourcesTld;

    @Column(name="IMPORT_DATETIME")
    private String importDatetime;

    @Column(name="RELATIONS_VIDEO")
    private String relationsVideo;

    @Column(name="VISITED_COUNT", nullable = false)
    @Builder.Default
    @NotNull
    private Long visitedCount = 0L;

    @Column(name="SCRAP_COUNT", nullable = false)
    @Builder.Default
    @NotNull
    private Long scrapCount = 0L;
}