package com.samsamoo.zzalu.tempGif.dto;

import com.amazonaws.services.ec2.model.CreateEgressOnlyInternetGatewayRequest;
import com.samsamoo.zzalu.amazonS3.upLoader.S3Uploader;
import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.tempGif.entity.TempGif;
import com.samsamoo.zzalu.tempGif.enums.RequestType;
import com.samsamoo.zzalu.tempGif.enums.RequestTypeConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@Setter
@RequiredArgsConstructor
public class TempGifRequest {

    private MultipartFile tempGifMultipartFile; // request
    private String description;
    private String tags;
//    private String returnUrl;
    private String relationsVideo;
    private Long originId; // return
    private String returnUrl;

    // to Entity method 만들기
    public TempGif toEntity(Member writer) {
        if (originId == null) { // create 요청
            // enum code C
            return TempGif.builder()
                    .gifPath(returnUrl)
                    .tags(tags)
                    .relationsVideo(relationsVideo)
                    .description(description)
                    .writerUsername(writer.getUsername())
                    .requestType(RequestType.CREATE)
                    .build();
        } else { // update 요청
            // enum code D
            return TempGif.builder()
                    .tags(tags)
                    .relationsVideo(relationsVideo)
                    .description(description)
                    .writerUsername(writer.getUsername())
                    .requestType(RequestType.UPDATE)
                    .originGifsId(originId)
                    .build();
        }
    }



}
