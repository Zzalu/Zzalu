package com.samsamoo.zzalu.ChatRoom.controller;

import com.samsamoo.zzalu.ChatRoom.entity.ChatRoomTest;
import com.samsamoo.zzalu.ChatRoom.service.ChatRoomServiceTest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatRoomControllerTest {

    @Autowired
    private final ChatRoomServiceTest chatRoomService;

    @PostMapping("/createRoom")
    @ResponseBody
    public String createRoom(@RequestParam("uid") Long uid, @RequestParam("roomName") String roomName, @RequestParam("description") String description, @RequestParam("tags") String tags, @RequestParam("roomImagePath") String roomImagePath){
        if(chatRoomService.createChatRoom(uid, roomName, description, tags, roomImagePath)) {
            System.out.println("정상적으로 생성되었습니다.");
            return "success";
        } else {
            System.out.println("고독방 생성에 실패했습니다.");
            return "failed";
        }
    }

    @GetMapping("/allRooms")
    @ResponseBody
    public List<ChatRoomTest> getAllRoom() {
        List<ChatRoomTest> chatRoomList = chatRoomService.getAllRoom();
        System.out.println(chatRoomList);
        return chatRoomList;
    }

    @GetMapping("/mainRooms")
    @ResponseBody
    public List<ChatRoomTest> getMainRoom() {
        List<ChatRoomTest> chatRoomList = chatRoomService.getMainRoom();
        System.out.println(chatRoomList);
        return chatRoomList;
    }
}
