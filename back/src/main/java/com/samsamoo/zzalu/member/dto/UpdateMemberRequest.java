package com.samsamoo.zzalu.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateMemberRequest {
    private MultipartFile profileMultipartFile;
    private String nickname;
    private String profileMessage;
}
