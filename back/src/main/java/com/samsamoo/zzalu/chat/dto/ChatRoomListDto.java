package com.samsamoo.zzalu.chat.dto;

import com.samsamoo.zzalu.chat.entity.ChatMessage;
import com.samsamoo.zzalu.chat.entity.ChatRoom;
import com.samsamoo.zzalu.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChatRoomListDto {
    private String roomId;
    private String roomName;
    private String userNmae;
    private Long memberId;
    private Long likeCount;

    private List<Long> chatMessageId;
    private List<Long> likeMemberId;
    private String imagePath;
    private String description;
    private String tags;
    private LocalDateTime enrollDate;
    private LocalDateTime lastActivation;

    public ChatRoomListDto(ChatRoom chatRoom) {
        roomId = chatRoom.getRoomId();
        roomName = chatRoom.getRoomName();
        userNmae = chatRoom.getUserNmae();
        memberId = chatRoom.getMemberId();
        likeCount = chatRoom.getLikeCount();
//        chatMessages = chatRoom.getChatMessages();
        chatMessageId = new ArrayList<>();
        for(ChatMessage chatMessage : chatRoom.getChatMessages()) {
            chatMessageId.add(chatMessage.getId());
        }
        likeMemberId = new ArrayList<>();
        for(Member member : chatRoom.getLikeMembers()) {
            likeMemberId.add(member.getId());
        }
        imagePath = chatRoom.getImagePath();
        description = chatRoom.getDescription();
        tags = chatRoom.getTags();
        enrollDate = chatRoom.getEnrollDate();
        lastActivation = chatRoom.getLastActivation();
    }
}
