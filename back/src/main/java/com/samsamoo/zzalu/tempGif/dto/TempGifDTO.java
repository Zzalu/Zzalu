package com.samsamoo.zzalu.tempGif.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@RequiredArgsConstructor
public class TempGifDTO {
    private MultipartFile tempGifMultipartFile; // request
    private String TempGifPath; // return
    private String description;
    private String tags;
    private String relationsVideo;
    private Long id; // return
}
