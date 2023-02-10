package com.samsamoo.zzalu.chat.controller;


import com.samsamoo.zzalu.auth.sevice.JwtTokenProvider;
import com.samsamoo.zzalu.chat.dto.ChatMessageDto;
import com.samsamoo.zzalu.chat.repository.ChatRoomRedisRepository;
import com.samsamoo.zzalu.kafka.service.KafkaProducer;
import com.samsamoo.zzalu.member.entity.Member;
import com.samsamoo.zzalu.redis.service.RedisPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatController {

    private final RedisPublisher redisPublisher;
    private final ChatRoomRedisRepository chatRoomRedisRepository;
    private final KafkaProducer kafkaProducer;
    private final JwtTokenProvider jwtTokenProvider;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        System.out.println(message.toString());
        System.out.println(message.getClass().getName());
        System.out.println("ChatController - ChatMessage : " + message);

        // 토큰 검사 => 에외 발생 시 Exception
        Member requestMember = jwtTokenProvider.getMember(message.getSender());
        message.setSender(requestMember.getNickname());
        message.setProfilePath(requestMember.getProfilePath());
        if (ChatMessageDto.MessageType.ENTER.equals(message.getType())) {
            chatRoomRedisRepository.enterChatRoom(message.getRoomId());
//            System.out.println("ChatController - if(ENTER) - findAllChatMessage : " + chatRoomRepository.findAllChatMessage(message.getRoomId()));
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }

        // kafka topic 발행
        kafkaProducer.sendMessage(message);

        // kafka topic 발행 정상 처리 이후 ChatMessage Redis에 저장
        // 입장 메시지 미저장 (입장이 아닐때만 저장)
        if(!ChatMessageDto.MessageType.ENTER.equals(message.getType())) {
            chatRoomRedisRepository.setChatMessage(message);
        }
//        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }

    @GetMapping("/chat/messages")
    public List<ChatMessageDto> getAllChatMessages(@RequestParam("roomId") String roomId) {
        return chatRoomRedisRepository.findAllChatMessage(roomId);
    }



}
