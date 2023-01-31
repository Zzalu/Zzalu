package com.samsamoo.zzalu.chat.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsamoo.zzalu.chat.dto.ChatMessage;
import com.samsamoo.zzalu.chat.dto.ChatRoom;
import com.samsamoo.zzalu.redis.service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;


@RequiredArgsConstructor
@Repository
public class ChatRoomRepository {
    // 채팅방(topic)에 발행되는 메시지를 처리할 Listner
    private final RedisMessageListenerContainer redisMessageListener;
    // 구독 처리 서비스
    private final RedisSubscriber redisSubscriber;
    // Redis
    private static final String CHAT_ROOMS = "CHAT_ROOM";
    private static final String CHAT_MESSAGES = "CHAT_MESSAGES";
    private final RedisTemplate<String, Object> redisTemplate;

    private final StringRedisTemplate stringRedisTemplate;

    private final RedisTemplate<String, ChatMessage> redisChatMessageTemplate;

    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
    // 채팅방의 대화 메시지를 발행하기 위한 redis topic 정보. 서버별로 채팅방에 매치되는 topic정보를 Map에 넣어 roomId로 찾을수 있도록 한다.
//    private Map<String, ChannelTopic> topics;
    private ValueOperations<String, String> opsStringTopic;

    // Created 2023.01.27 by Hye Sung
    private ListOperations<String, ChatMessage> opsListChatMessage;

    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
        opsStringTopic = stringRedisTemplate.opsForValue();
        opsListChatMessage = redisChatMessageTemplate.opsForList();
//        topics = new HashMap<>();
    }

    // Created 2023.01.27 by Hye Sung
    @Cacheable(cacheNames = "ChatMessages", key = "#id + #id")
    public List<ChatMessage> findAllChatMessage(String id) {
        System.out.println("ChatRoomRepository - findAllChatMessage : " + opsListChatMessage.size(id + id));
        System.out.println("Cache 미적용");
        long size = opsListChatMessage.size(id + id);
        List<ChatMessage> chatMessages = opsListChatMessage.range(id + id, 0, size);
       return chatMessages;
    }

    public List<ChatRoom> findAllRoom() {
        return opsHashChatRoom.values(CHAT_ROOMS);
    }

    public ChatRoom findRoomById(String id) {
        return opsHashChatRoom.get(CHAT_ROOMS, id);
    }

    /**
     * 채팅방 생성 : 서버간 채팅방 공유를 위해 redis hash에 저장한다.
     */
    public ChatRoom createChatRoom(String name) {
        ChatRoom chatRoom = ChatRoom.create(name);
        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }


    @CacheEvict(value = "ChatMessages", key = "#message.getRoomId() + #message.getRoomId()", allEntries = true)
    public void setChatMessage(ChatMessage message) {
        System.out.println("ChatRoomRepository - getChatMessage");
        String topic = opsStringTopic.get(message.getRoomId());
        ChatRoom chatRoom = opsHashChatRoom.get(CHAT_ROOMS, topic);
        opsListChatMessage.rightPush(message.getRoomId() + message.getRoomId(), message);
        System.out.println("==============================================");
        chatRoom.setChatMessageCount(chatRoom.getChatMessageCount() + 1);
    }

    /**
     * 채팅방 입장 : redis에 topic을 만들고 pub/sub 통신을 하기 위해 리스너를 설정한다.
     */
    public void enterChatRoom(String roomId) {
//        ChannelTopic topic = topics.get(roomId);
        System.out.println("ChatRoomRepository - enterChatRoom - get : " + opsStringTopic.get(roomId));
        System.out.println("ChatRoomRepository - enterChatRoom - TOPIC : " + new ChannelTopic(roomId));
        String topic = opsStringTopic.get(roomId);
        if (topic == null) {
            redisMessageListener.addMessageListener(redisSubscriber, new ChannelTopic(roomId));
//            topics.put(roomId, topic);
            opsStringTopic.set(roomId, String.valueOf(new ChannelTopic(roomId)));
        }
        redisMessageListener.addMessageListener(redisSubscriber, new ChannelTopic(roomId));
    }

    public ChannelTopic getTopic(String roomId) {
       return ChannelTopic.of(opsStringTopic.get(roomId));
//        return topics.get(roomId);
    }
}
