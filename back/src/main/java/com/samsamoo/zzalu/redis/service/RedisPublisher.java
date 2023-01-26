package com.samsamoo.zzalu.redis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsamoo.zzalu.chat.dto.ChatMessage;
import com.samsamoo.zzalu.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChatRoomRepository chatRoomRepository;

    @KafkaListener(topics="exam", groupId = "foo")
    public void kafkaPublish(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ChatMessage chatMessage = objectMapper.readValue(message, ChatMessage.class);
        redisTemplate.convertAndSend(((ChannelTopic) chatRoomRepository.getTopic(chatMessage.getRoomId())).getTopic(), chatMessage);
    }

    public void publish(ChannelTopic topic, ChatMessage message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
