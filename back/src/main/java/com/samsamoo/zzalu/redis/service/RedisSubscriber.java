package com.samsamoo.zzalu.redis.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsamoo.zzalu.TitleHakwon.dto.CommentResponse;
import com.samsamoo.zzalu.chat.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final RedisTemplate redisTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

/*
    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {

            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());
            ChatMessageDto roomMessage = objectMapper.readValue(publishMessage, ChatMessageDto.class);
            messagingTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getRoomId(), roomMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
*/

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {


            String topicc = (String) redisTemplate.getStringSerializer().deserialize(message.getChannel());
            String publishMessage = (String) redisTemplate.getStringSerializer().deserialize(message.getBody());

            if(topicc.equals("comments")){
                System.out.println("comments");
                CommentResponse commentResponse =  objectMapper.readValue(publishMessage, CommentResponse.class);
                messagingTemplate.convertAndSend("/sub/title-hakwon/comments/", commentResponse);
            }else{
                ChatMessage roomMessage = objectMapper.readValue(publishMessage, ChatMessage.class);
                messagingTemplate.convertAndSend("/sub/chat/room/" + roomMessage.getRoomId(), roomMessage);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
