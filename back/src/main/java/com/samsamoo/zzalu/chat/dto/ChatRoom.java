package com.samsamoo.zzalu.chat.dto;

import com.samsamoo.zzalu.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@RedisHash(value="CHAT_ROOM")
public class ChatRoom  implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;
    private String roomId;
    private String roomName;
    private String userNmae;
    private Long memberId;
    private Long likeCount;

    private List<ChatMessage> chatMessages;
    private HashMap<Member, Boolean> likeMembers;
    private String imagePath;
    private String description;
    private String tags;
    private LocalDateTime enrollDate;
    private LocalDateTime lastActivation;

    public static ChatRoom create(ChatRoomEnroll chatRoomEnroll) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.userNmae = chatRoomEnroll.getUserName();
        chatRoom.memberId = chatRoomEnroll.getMemberId();
        chatRoom.roomName = chatRoomEnroll.getRoomName();
        chatRoom.imagePath = chatRoomEnroll.getImagePath();
        chatRoom.description = chatRoomEnroll.getDescription();
        chatRoom.tags = chatRoomEnroll.getTags();
        chatRoom.chatMessages = new LinkedList<ChatMessage>();
        chatRoom.likeMembers = new HashMap<Member, Boolean>();
        chatRoom.likeCount = 0L;
        chatRoom.enrollDate = LocalDateTime.now();
        chatRoom.lastActivation = LocalDateTime.now();
        return chatRoom;
    }
}
