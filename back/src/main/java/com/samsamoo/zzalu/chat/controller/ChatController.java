package com.samsamoo.zzalu.chat.controller;


import com.samsamoo.zzalu.chat.dto.ChatMessage;
import com.samsamoo.zzalu.chat.repository.ChatRoomRepository;
import com.samsamoo.zzalu.kafka.service.KafkaProducer;
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
    private final ChatRoomRepository chatRoomRepository;

    private final KafkaProducer kafkaProducer;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        System.out.println(message.getClass().getName());
        System.out.println("ChatController - ChatMessage : " + message);
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            chatRoomRepository.enterChatRoom(message.getRoomId());
            chatRoomRepository.findAllChatMessage(message.getRoomId());
            List<ChatMessage> chatMessages = chatRoomRepository.findAllChatMessage(message.getRoomId());
            for(ChatMessage cm : chatMessages) {
                System.out.println(cm.getSender() + " : " + cm.getMessage());
            }
//            System.out.println("ChatController - if(ENTER) - findAllChatMessage : " + chatRoomRepository.findAllChatMessage(message.getRoomId()));
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");

        }

        // kafka topic 발행
        kafkaProducer.sendMessage(message);

        // kafka topic 발행 정상 처리 이후 ChatMessage Redis에 저장
        // 입장 메시지 미저장 (입장이 아닐때만 저장)
        if(!ChatMessage.MessageType.ENTER.equals(message.getType())) {
            chatRoomRepository.setChatMessage(message);
        }

//        redisPublisher.publish(chatRoomRepository.getTopic(message.getRoomId()), message);
    }
}
