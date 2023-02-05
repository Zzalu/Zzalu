package com.samsamoo.zzalu.chat.repository;

import com.samsamoo.zzalu.chat.dto.ChatRoom;
import com.samsamoo.zzalu.chat.dto.ChatRoomEnroll;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ChatRepository {
    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    public ChatRoom findRoomById(String Id) {
        return chatRoomMap.get(Id);
    }

    public ChatRoom createChatRoom(ChatRoomEnroll chatRoomEnroll) {
        ChatRoom chatRoom = ChatRoom.create(chatRoomEnroll);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
