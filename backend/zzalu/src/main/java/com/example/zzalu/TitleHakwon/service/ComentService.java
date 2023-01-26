package com.example.zzalu.TitleHakwon.service;


import com.example.zzalu.TitleHakwon.entity.Coment;
import com.example.zzalu.TitleHakwon.repository.ComentRepository;
import com.example.zzalu.TitleHakwon.repository.TitleHackwonRepository;
import com.example.zzalu.User.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.example.zzalu.TitleHakwon.dto.RequestComent;


import javax.annotation.PostConstruct;

@RequiredArgsConstructor  //얘가 자동으로 생성자 주입해줌
@Repository
public class ComentService {


    private static final String COMENT="TitleHakwon:COMENT";

    private final RedisTemplate<String,Object> redisTemplate;


    private final ModelMapper modelMapper;
    private HashOperations<String,Long, Coment> opsHashComent;

    private final ComentRepository comentRepository;
    private final MemberRepository memberRepository;

    private final TitleHackwonRepository titleHackwonRepository;

    @PostConstruct
    private void init(){
        opsHashComent = redisTemplate.opsForHash();
    }

    /**
     * 댓글 작성
     */
    public Coment addComent (RequestComent requestComent){

        //Coment coment = new Coment().
       // System.out.println(titleHakwonComent.getId()+"댓글 아이디 값");
        //opsHashComent.put(COMENT,titleHakwonComent.getId(),titleHakwonComent);
        //return titleHakwonComent;


/*
        System.out.println(requestComent.getClassNum());
        Coment coment = modelMapper.map(requestComent,Coment.class);
        System.out.println(coment);


*/

        Coment comentEntity = Coment.builder()
                .member(memberRepository.findMemberByMemberId(requestComent.getMemberId()))
                .titleHakwon(titleHackwonRepository.findTitleHakwonById(requestComent.getTitleHakwonId()))
                .cotent(requestComent.getContent())
                .parentComentId(requestComent.getParentComentId())
                .comentOrder(requestComent.getComentOrder())
                .build();


        comentRepository.save(comentEntity);
        System.out.println(requestComent.getTitleHakwonId());

       System.out.println(titleHackwonRepository.findTitleHakwonById(requestComent.getTitleHakwonId()).getComents().size());



        //Dto로 변환 시켰었으면 Entity를 다시 Dto로 변환해서 보내줘야하나?
        return comentEntity;




    }

    public Coment findTitleHakwonComent (Long id){
        return opsHashComent.get(COMENT,id);
    }



}
