package com.example.zzalu.TitleHakwon;


import com.example.zzalu.TitleHakwon.model.TitleHakwonComent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Repository
public class ComentRepository {


    private static final String COMENT="TitleHakwon:COMENT";

    private final RedisTemplate<String,Object> redisTemplate;
    private HashOperations<String,Long, TitleHakwonComent> opsHashComent;

    @PostConstruct
    private void init(){
        opsHashComent = redisTemplate.opsForHash();
    }

    /**
     * 댓글 생성
     */

    public TitleHakwonComent createTitleHakwonComent (TitleHakwonComent titleHakwonComent){
        System.out.println(titleHakwonComent.getId()+"댓글 아이디 값");
        opsHashComent.put(COMENT,titleHakwonComent.getId(),titleHakwonComent);
        return titleHakwonComent;
    }

    public TitleHakwonComent findTitleHakwonComent (Long id){
        return opsHashComent.get(COMENT,id);
    }



}
