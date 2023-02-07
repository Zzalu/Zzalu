package com.samsamoo.zzalu.redis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsamoo.zzalu.chat.dto.ChatMessageDto;
import com.samsamoo.zzalu.chat.repository.ChatRoomRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChatRoomRedisRepository chatRoomRedisRepository;

    @KafkaListener(topics="exam", groupId = "foo")
    public void kafkaListener(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ChatMessageDto chatMessageDto = objectMapper.readValue(message, ChatMessageDto.class);
        redisTemplate.convertAndSend(((ChannelTopic) chatRoomRedisRepository.getTopic(chatMessageDto.getRoomId())).getTopic(), chatMessageDto);
    }

    public void publish(ChannelTopic topic, ChatMessageDto message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
