package com.samsamoo.zzalu.chat.controller;

import com.samsamoo.zzalu.chat.dto.ChatRoom;
import com.samsamoo.zzalu.chat.dto.ChatRoomEnroll;
import com.samsamoo.zzalu.chat.repository.ChatRoomRepository;
import com.samsamoo.zzalu.member.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

//    @GetMapping("/room")
//    public String rooms(Model model) {
//        return "chat/room";
//    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> rooms() {
        return chatRoomRepository.findAllRoom();
    }

    @GetMapping("/rooms-top10")
    @ResponseBody
    public List<ChatRoom> roomsLikeTop10() {
        return chatRoomRepository.findTop10LikeCountRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestBody ChatRoomEnroll chatRoomEnroll) {
        System.out.println("chatroomenroll");
        System.out.println(chatRoomEnroll.toString());
        return chatRoomRepository.createChatRoom(chatRoomEnroll);
    }

//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "chat/roomdetail";
//    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
