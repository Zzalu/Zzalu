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

import java.time.LocalDateTime;
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

        // 토큰 검사 => 에외 발생 시 Exception
        Member requestMember = jwtTokenProvider.getMember(message.getSender());
        message.setSender(requestMember.getNickname());
        message.setMemberName(requestMember.getUsername());
        message.setProfilePath(requestMember.getProfilePath());
        message.setMemberId(requestMember.getId());
        message.setSendDate(LocalDateTime.now());

        if (ChatMessageDto.MessageType.ENTER.equals(message.getType())) {
            chatRoomRedisRepository.enterChatRoom(message.getRoomId());
        }

        // kafka topic 발행
        kafkaProducer.sendMessage(message);
        // 입장이 아닐때만 저장
        if (!ChatMessageDto.MessageType.ENTER.equals(message.getType())) {
            chatRoomRedisRepository.setChatMessage(message);
        }
//        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }
}
