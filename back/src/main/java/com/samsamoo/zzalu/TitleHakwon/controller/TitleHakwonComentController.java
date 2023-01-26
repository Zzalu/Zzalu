package com.samsamoo.zzalu.TitleHakwon.controller;

import com.samsamoo.zzalu.TitleHakwon.repository.ComentRepository;
import com.samsamoo.zzalu.TitleHakwon.model.TitleHakwonComent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coment")
public class TitleHakwonComentController {


    private final ComentRepository comentRepository;

    @GetMapping("/{comentId}")

    public TitleHakwonComent getTitleHakwonComent(@PathVariable Long comentId){

        System.out.println("[찾고 싶은 댓글의 id 값]"+comentId);
        return comentRepository.findTitleHakwonComent(comentId);
    }


    /**
     * redis에 댓글 저장하기
     * @param titleHakwonComent
     * @return
     *
     *
     * 엔티티를
     */
    @PostMapping
    public TitleHakwonComent createTitleHakwonComentWithRedis (@RequestBody TitleHakwonComent titleHakwonComent){

           return comentRepository.createTitleHakwonComent(titleHakwonComent);
    }

}
