package com.samsamoo.zzalu.gif.dto;

import com.samsamoo.zzalu.gif.entity.GiphyGif;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GiphyGifDto {

    private Long id;

    private String gifPath;

    private Long useCount;

    private Long downloadCount;

    private Long likeCount;

    private String tags;

    private String sources;

    private String sourcesPostUrl;

    private String sourcesTld;

    private String importDatetime;

    private String relationsVideo;

    private long visitedCount;

    private long scrapCount;

    public GiphyGifDto(GiphyGif giphyGif) {
        this.id = giphyGif.getId();
        this.gifPath = giphyGif.getGifPath();
        this.useCount = giphyGif.getUseCount();
        this.downloadCount = giphyGif.getDownloadCount();
        this.likeCount = giphyGif.getLikeCount();
        this.tags = giphyGif.getTags();
        this.sources = giphyGif.getSources();
        this.sourcesPostUrl = giphyGif.getSourcesPostUrl();
        this.sourcesTld = giphyGif.getSourcesTld();
        this.importDatetime = giphyGif.getImportDatetime();
        this.relationsVideo = giphyGif.getRelationsVideo();
        this.visitedCount = giphyGif.getVisitedCount();
        this.scrapCount = giphyGif.getScrapCount();
    }
}
