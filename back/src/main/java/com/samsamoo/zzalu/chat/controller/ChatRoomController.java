package com.samsamoo.zzalu.chat.controller;

import com.nimbusds.oauth2.sdk.token.AccessToken;
import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.chat.dto.ChatRoomDto;
import com.samsamoo.zzalu.chat.dto.ChatRoomEnroll;
import com.samsamoo.zzalu.chat.entity.ChatRoom;
import com.samsamoo.zzalu.chat.repository.ChatRoomRedisRepository;
import com.samsamoo.zzalu.chat.repository.ChatRoomRepository;
import com.samsamoo.zzalu.chat.service.ChatRoomService;
import com.samsamoo.zzalu.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatRoomController {

    private final ChatRoomRedisRepository chatRoomRedisRepository;
    private final ChatRoomService chatRoomService;
    private final ChatRoomRepository chatRoomRepository;
    private final JwtTokenProvider jwtTokenProvider;

    // =============== redis ======================
//    @GetMapping("/rooms")
//    @ResponseBody
//    public List<ChatRoomDto> rooms() {
//        return chatRoomRedisRepository.findAllRoom();
//    }
//
//    @GetMapping("/rooms-top10")
//    @ResponseBody
//    public List<ChatRoomDto> roomsLikeTop10() {
//        return chatRoomRedisRepository.findTop10LikeCountRoom();
//    }
//
//    @PostMapping("/room")
//    @ResponseBody
//    public ChatRoomDto createRoom(@RequestBody ChatRoomEnroll chatRoomEnroll) {
//        System.out.println("chatroomenroll");
//        System.out.println(chatRoomEnroll.toString());
//        return chatRoomRedisRepository.createChatRoom(chatRoomEnroll);
//    }
//
////    @GetMapping("/room/enter/{roomId}")
////    public String roomDetail(Model model, @PathVariable String roomId) {
////        model.addAttribute("roomId", roomId);
////        return "chat/roomdetail";
////    }
//
//    @GetMapping("/room/{roomId}")
//    @ResponseBody
//    public ChatRoomDto roomInfo(@PathVariable String roomId) {
//        return chatRoomRedisRepository.findRoomById(roomId);
//    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> findAll() {
        return chatRoomService.findAll();
    }

    @GetMapping("/rooms-top10")
    @ResponseBody
    public List<ChatRoom> findTop10ByOrderByLikeCountDesc() {
        return chatRoomService.findTop10ByOrderByLikeCountDesc();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoomDto createRoom(@RequestBody ChatRoomEnroll chatRoomEnroll) {
        return chatRoomRedisRepository.createChatRoom(chatRoomEnroll);
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomDto roomInfo(@PathVariable String roomId) {
        return chatRoomRedisRepository.findRoomById(roomId);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<ChatRoom> findByTagsContainsOrRoomNameContains(@RequestParam(name = "keyword") String keyword){
        return chatRoomService.findByTagsContainsOrRoomNameContains(keyword);
    }

    @GetMapping("/search-tags")
    @ResponseBody
    public List<ChatRoom> findByTagsContains(@RequestParam(name = "keyword") String keyword){
        return chatRoomService.findByTagsContains(keyword);
    }

    @GetMapping("/search-room-name")
    @ResponseBody
    public List<ChatRoom> findByRoomNameContains(@RequestParam(name = "keyword") String keyword){
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
    public List<ChatRoom> findAllByMemberId(@RequestHeader(value = "Authorization")String bearerToken){
        String token = bearerToken.substring(7);
        Member requestMember = jwtTokenProvider.getMember(token);
        return chatRoomService.findAllByMemberId(requestMember.getId());
    }

    @GetMapping("/member-order-lastactivation")
    @ResponseBody
    public List<ChatRoom> findAllByMemberIdOrderByLastActivationDesc(@RequestHeader(value = "Authorization")String bearerToken){
        String token = bearerToken.substring(7);
        Member requestMember = jwtTokenProvider.getMember(token);
        return chatRoomService.findAllByMemberIdOrderByLastActivationDesc(requestMember.getId());
    }

    @GetMapping("/member-order-likecount")
    @ResponseBody
    public List<ChatRoom> findAllByMemberIdOrderByLikeCountDesc(@RequestHeader(value = "Authorization")String bearerToken){
        String token = bearerToken.substring(7);
        Member requestMember = jwtTokenProvider.getMember(token);
        return chatRoomService.findAllByMemberIdOrderByLikeCountDesc(requestMember.getId());
    }

    @GetMapping("/search-member-order-lastactivation")
    @ResponseBody
    public List<ChatRoom> findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLastActivationDesc(@RequestParam(name = "keyword") String keyword,@RequestHeader(value = "Authorization")String bearerToken){
        String token = bearerToken.substring(7);
        Member requestMember = jwtTokenProvider.getMember(token);
        return chatRoomService.findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLastActivationDesc(requestMember.getId(), keyword, keyword);
    }

    @GetMapping("/search-member-order-likecount")
    @ResponseBody
    public List<ChatRoom> findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLikeCountDesc(@RequestParam(name = "keyword") String keyword, @RequestHeader(value = "Authorization")String bearerToken){
        String token = bearerToken.substring(7);
        Member requestMember = jwtTokenProvider.getMember(token);
        return chatRoomService.findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLikeCountDesc(requestMember.getId(), keyword, keyword);
    }

    // 사용자 인증 정보 식별을 위한 Token 필요
    // 좋아요를 누른 방 아이디 필요
    // Dto 구성해서 수행
    @PostMapping("/like")
    @ResponseBody
    public boolean updateLikeCount(@RequestHeader(value = "Authorization")String bearerToken, @RequestBody Map<String, String> map){

        // AccessToken 검증 필요
        String token = bearerToken.substring(7);
        Member requestMember = jwtTokenProvider.getMember(token);

        String roomId = map.get("roomId");
        System.out.println("RoomId : " + roomId);
        Optional<ChatRoom> optionalChatRoom = chatRoomService.findByRoomId(roomId);
        if(optionalChatRoom.isPresent()) {
            ChatRoom chatRoom = optionalChatRoom.get();
            System.out.println("chatRoom.getLikeMembers() : " + chatRoom.getLikeMembers());
            if(!chatRoom.getLikeMembers().contains(requestMember)) {
                requestMember.addLikeChatRoom(chatRoom);
                chatRoom.setLikeCount(chatRoom.getLikeCount() + 1);
                chatRoomService.save(chatRoom);
                return true;
            } else {
                System.out.println("이미 클릭한 사용자 입니다. Error Exception 필요");
                return false;
            }
        }
        else {
            System.out.println("Can't find chatRoom Error");
            return false;
        }

    }
}
