package com.samsamoo.zzalu.titlehakwon.repository;

import com.samsamoo.zzalu.redis.service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class RedisCommentRepository {

    private Map<String, ChannelTopic> titleTopics;

    private final RedisMessageListenerContainer redisMessageListener;

    private final RedisSubscriber redisSubscriber;
    @PostConstruct
    private void init (){
        titleTopics= new HashMap<>();
        // 댓글 Topic
        titleTopics.put("comments",new ChannelTopic("comments"));
        // 좋아요 Topic
        titleTopics.put("likes",new ChannelTopic("likes"));


        //리스너 연결
        redisMessageListener.addMessageListener(redisSubscriber, titleTopics.get("comments"));
        redisMessageListener.addMessageListener(redisSubscriber, titleTopics.get("likes"));


    }

    public ChannelTopic getTopic (String topic){
        return titleTopics.get(topic);
    }


}
