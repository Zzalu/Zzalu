//package com.samsamoo.zzalu.gif.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//
//@Entity
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class GiphyGif {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="ID")
//    private Long id;
//
//    @Column(name="GIF_PATH", nullable = false, unique = true)
//    private String gifPath;
//
//    @Column(name="USE_COUNT", nullable = false)
//    @Builder.Default
//    private Long useCount = 0L;
//
//    @Column(name="DOWNLOAD_COUNT", nullable = false)
//    @Builder.Default
//    private Long downloadCount = 0L;
//
//    @Column(name="LIKE_COUNT", nullable = false)
//    @Builder.Default
//    private Long likeCount = 0L;
//
//    @Column(name="TAGS")
//    private String tags;
//
//    @Column(name="SORUCES")
//    private String sources;
//
//    @Column(name="SORUCES_POST_URL")
//    private String sourcesPostUrl;
//
//    @Column(name="SORUCES_TLD")
//    private String sourcesTld;
//
//    @Column(name="IMPORT_DATETIME")
//    private String importDatetime;
//
//    @Column(name="RELATIONS_VIDEO")
//    private String relationsVideo;
//
//    @Column(name="VISITED_COUNT", nullable = false)
//    @Builder.Default
//    private Long visitedCount = 0L;
//
//    @Column(name="SCRAP_COUNT", nullable = false)
//    @Builder.Default
//    private Long scrapCount = 0L;
//}
