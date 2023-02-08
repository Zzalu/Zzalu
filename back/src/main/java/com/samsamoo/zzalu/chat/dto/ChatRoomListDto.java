package com.samsamoo.zzalu.chat.dto;

import com.samsamoo.zzalu.member.entity.Member;

import java.time.LocalDateTime;
import java.util.List;

public class ChatRoomListDto {
    private String roomId;
    private String roomName;
    private String userNmae;
    private Long memberId;
    private Long likeCount;

    private List<ChatMessageDto> chatMessageDtos;
    private List<Long> likeMembersId;
    private String imagePath;
    private String description;
    private String tags;
    private LocalDateTime enrollDate;
    private LocalDateTime lastActivation;

}
