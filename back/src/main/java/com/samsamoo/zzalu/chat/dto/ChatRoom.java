package com.samsamoo.zzalu.chat.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RedisHash(value="CHAT_ROOM")
public class ChatRoom  implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;
    private String roomId;
    private String name;
    private List<ChatMessage> chatMessages;
    private String imagePath;
    private LocalDateTime enrollDate;
    private LocalDateTime lastActivation;

    public static ChatRoom create(String name, String imagePath) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        chatRoom.imagePath = imagePath;
        chatRoom.enrollDate = LocalDateTime.now();
        return chatRoom;
    }
}
