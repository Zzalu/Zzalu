package com.samsamoo.zzalu.gif.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="GIPHY_GIF")
public class giphyGif implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="GIF_PATH")
    private String gifPath;

    @Column(name="USE_COUNT")
    private long useCount;

    @Column(name="DOWNLOAD_COUNT")
    private long downloadCount;

    @Column(name="LIKE_COUNT")
    private String likeCount;

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

    @Column(name="VISITED_COUNT")
    private long visitedCount;

    @Column(name="SCRAP_COUNT")
    private long scrapCount;

}
