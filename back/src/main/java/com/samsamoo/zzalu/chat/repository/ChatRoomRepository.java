package com.samsamoo.zzalu.chat.repository;

import com.samsamoo.zzalu.chat.dto.ChatRoom;
import com.samsamoo.zzalu.redis.service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final RedisTemplate<String, Object> redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
    // 채팅방의 대화 메시지를 발행하기 위한 redis topic 정보. 서버별로 채팅방에 매치되는 topic정보를 Map에 넣어 roomId로 찾을수 있도록 한다.
//    private Map<String, ChannelTopic> topics;
    private ValueOperations<String, String> opsStringTopic;

    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
        opsStringTopic = stringRedisTemplate.opsForValue();
//        topics = new HashMap<>();
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
