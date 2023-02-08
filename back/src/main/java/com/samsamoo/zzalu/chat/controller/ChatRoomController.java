package com.samsamoo.zzalu.chat.controller;

import com.samsamoo.zzalu.chat.dto.ChatRoomDto;
import com.samsamoo.zzalu.chat.dto.ChatRoomEnroll;
import com.samsamoo.zzalu.chat.repository.ChatRoomRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatRoomController {

    private final ChatRoomRedisRepository chatRoomRedisRepository;

//    @GetMapping("/room")
//    public String rooms(Model model) {
//        return "chat/room";
//    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoomDto> rooms() {
        return chatRoomRedisRepository.findAllRoom();
    }

    @GetMapping("/rooms-top10")
    @ResponseBody
    public List<ChatRoomDto> roomsLikeTop10() {
        return chatRoomRedisRepository.findTop10LikeCountRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoomDto createRoom(@RequestBody ChatRoomEnroll chatRoomEnroll) {
        System.out.println("chatroomenroll");
        System.out.println(chatRoomEnroll.toString());
        return chatRoomRedisRepository.createChatRoom(chatRoomEnroll);
    }

//    @GetMapping("/room/enter/{roomId}")
//    public String roomDetail(Model model, @PathVariable String roomId) {
//        model.addAttribute("roomId", roomId);
//        return "chat/roomdetail";
//    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomDto roomInfo(@PathVariable String roomId) {
        return chatRoomRedisRepository.findRoomById(roomId);
    }
}
