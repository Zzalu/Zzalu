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

    private final ModelMapper modelMapper;


    private final ComentRepository comentRepository;
    private final MemberRepository memberRepository;

    private final TitleHackwonRepository titleHackwonRepository;

    /**
     * 댓글 작성
     */
    public Coment addComent (RequestComent requestComent){

        /** Comment 엔티티 생성 후 저장 **/

        Coment comentEntity = Coment.builder()
                .member(memberRepository.findMemberByMemberId(requestComent.getMemberId()))
                .titleHakwon(titleHackwonRepository.findTitleHakwonById(requestComent.getTitleHakwonId()))
                .cotent(requestComent.getContent())
                .parentComentId(requestComent.getParentComentId())
                .comentOrder(requestComent.getComentOrder())
                .build();


        comentRepository.save(comentEntity);
        System.out.println(requestComent.getTitleHakwonId());


        //Dto로 변환 시켰었으면 Entity를 다시 Dto로 변환해서 보내줘야하나?
        return comentEntity;


    }

}
