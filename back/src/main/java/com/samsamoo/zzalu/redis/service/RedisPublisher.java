package com.samsamoo.zzalu.redis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsamoo.zzalu.TitleHakwon.dto.CommentResponse;
import com.samsamoo.zzalu.TitleHakwon.dto.LikeResponse;

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

    public void publishTitleHakwon(ChannelTopic topic, CommentResponse commentResponse) {
        System.out.println("[publishTItlehakwon]"+ topic.getTopic());
        System.out.println(commentResponse.getContent()+"댓글 정보");
        redisTemplate.convertAndSend(topic.getTopic(), commentResponse);
    }

    public void pubLikes(ChannelTopic topic, LikeResponse likeResponse) {
        System.out.println("[publish Likes]"+ topic.getTopic());
        System.out.println(likeResponse.getLikeNum()+"좋아요 정보");
        redisTemplate.convertAndSend(topic.getTopic(), likeResponse);
    }
}
