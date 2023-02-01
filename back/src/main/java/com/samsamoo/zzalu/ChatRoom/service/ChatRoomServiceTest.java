package com.samsamoo.zzalu.ChatRoom.service;

import com.samsamoo.zzalu.ChatRoom.dto.ChatRoomDtoTest;
import com.samsamoo.zzalu.ChatRoom.entity.ChatRoomTest;
import com.samsamoo.zzalu.ChatRoom.repository.ChatRoomRepositoryTest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceTest {

    @Autowired
    private final ChatRoomRepositoryTest chatRoomRepository;

    public boolean createChatRoom(Long uid, String roomName, String description, String tags, String roomImagePath) {
        if(roomName == null)
            return false;
        ChatRoomDtoTest chatRoomDto = ChatRoomDtoTest.create(uid, roomName, description, tags, roomImagePath);
        Optional<ChatRoomTest> chatRoom = chatRoomRepository.findByRoomNumber(chatRoomDto.getRoomNumber());
        if(chatRoom.isPresent()) {
            System.out.println("채팅방 중복 에러 발생");
            return false;
        }
        else {
            System.out.println("getEnrollDate");
            System.out.println(chatRoomRepository.save(chatRoomDto.toEntity()).getEnrollDate());
            return true;
        }
    }

    public List<ChatRoomTest> getAllRoom() {
        return chatRoomRepository.findAll();
    }

    public List<ChatRoomTest> getMainRoom() {
        return chatRoomRepository.findTop10ByOrderByLikeCountDesc();
    }
}
