package com.samsamoo.zzalu.chat.controller;

import com.samsamoo.zzalu.chat.dto.ChatRoomDto;
import com.samsamoo.zzalu.chat.dto.ChatRoomEnroll;
import com.samsamoo.zzalu.chat.entity.ChatRoom;
import com.samsamoo.zzalu.chat.repository.ChatRoomRedisRepository;
import com.samsamoo.zzalu.chat.service.ChatRoomService;
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
    private final ChatRoomService chatRoomService;

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

    @GetMapping("/search")
    @ResponseBody
    public List<ChatRoom> findByTagsContainsOrRoomNameContains(@RequestParam(name = "keyword") String keyword){
        System.out.println(keyword);
        return chatRoomService.findByTagsContainsOrRoomNameContains(keyword);
    }

    @GetMapping("/search-tags")
    @ResponseBody
    public List<ChatRoom> findByTagsContains(@RequestParam(name = "keyword") String keyword){
        System.out.println(keyword);
        return chatRoomService.findByTagsContains(keyword);
    }

    @GetMapping("/search-room-name")
    @ResponseBody
    public List<ChatRoom> findByRoomNameContains(@RequestParam(name = "keyword") String keyword){
        System.out.println(keyword);
        return chatRoomService.findByRoomNameContains(keyword);
    }

    @GetMapping("/rooms-jpa")
    @ResponseBody
    public List<ChatRoom> roomsJpa() {
        return chatRoomService.findAll();
    }

    @GetMapping("/search-order-lastactivation")
    @ResponseBody
    public List<ChatRoom> findAllByTagsContainsOrRoomNameContainsOrderByLastActivationDesc(@RequestParam(name = "keyword") String keyword){
        return chatRoomService.findAllByTagsContainsOrRoomNameContainsOrderByLastActivationDesc(keyword, keyword);
    }
//
    @GetMapping("/search-order-likecount")
    @ResponseBody
    public List<ChatRoom> findAllByTagsContainsOrRoomNameContainsOrderByLikeCount(@RequestParam(name = "keyword") String keyword){
        return chatRoomService.findAllByTagsContainsOrRoomNameContainsOrderByLikeCount(keyword, keyword);
    }

    @GetMapping("/member")
    @ResponseBody
    public List<ChatRoom> findAllByMemberId(@RequestParam(name = "memberId") Long memberId){
        return chatRoomService.findAllByMemberId(memberId);
    }

    @GetMapping("/member-order-lastactivation")
    @ResponseBody
    public List<ChatRoom> findAllByMemberIdOrderByLastActivationDesc(@RequestParam(name = "memberId") Long memberId){
        return chatRoomService.findAllByMemberIdOrderByLastActivationDesc(memberId);
    }

    @GetMapping("/member-order-likecount")
    @ResponseBody
    public List<ChatRoom> findAllByMemberIdOrderByLikeCountDesc(@RequestParam(name = "memberId") Long memberId){
        return chatRoomService.findAllByMemberIdOrderByLikeCountDesc(memberId);
    }

    @GetMapping("/search-member-order-lastactivation")
    @ResponseBody
    public List<ChatRoom> findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLastActivationDesc(@RequestParam(name = "keyword") String keyword, @RequestParam(name = "memberId") Long memberId){
        return chatRoomService.findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLastActivationDesc(memberId, keyword, keyword);
    }

    @GetMapping("/search-member-order-likecount")
    @ResponseBody
    public List<ChatRoom> findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLikeCountDesc(@RequestParam(name = "keyword") String keyword, @RequestParam(name = "memberId") Long memberId){
        return chatRoomService.findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLikeCountDesc(memberId, keyword, keyword);
    }

}
