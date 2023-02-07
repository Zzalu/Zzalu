package com.samsamoo.zzalu.gifs.dto;

import com.samsamoo.zzalu.gifs.entity.Gifs;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GifsUpdateDto {

    private Long id;
    private String tags;
    private String sources;
    private String sourcesPostUrl;
    private String sourcesTld;
    private String relationsVideo;
    private String description;
}
