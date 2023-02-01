package com.samsamoo.zzalu.ChatRoom.dto;

import com.samsamoo.zzalu.ChatRoom.entity.ChatRoomTest;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@RedisHash(value="CHAT_ROOM")
public class ChatRoomDtoTest implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;
    private Long id;
    private Long uid;
    private String roomNumber;
    private String roomName;
    private String roomImagePath;
    private Long likeCount;
    private String description;
    private String tags;


    public static ChatRoomDtoTest create(Long uid, String roomName, String description, String tags, String roomImagePath) {
        ChatRoomDtoTest chatRoom = new ChatRoomDtoTest();
        chatRoom.uid = uid;
        chatRoom.roomNumber = UUID.randomUUID().toString();
        chatRoom.roomName = roomName;
        chatRoom.description = description;
        chatRoom.roomImagePath = roomImagePath;
        chatRoom.tags = tags;
        return chatRoom;
    }

    public ChatRoomTest toEntity() {
        return ChatRoomTest.builder()
                .uid(uid)
                .roomNumber(roomNumber)
                .roomName(roomName)
                .roomImagePath(roomImagePath)
                .description(description)
                .tags(tags)
                .build();
    }

}
