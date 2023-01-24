package com.example.zzalu.TitleHakwon.controller;

import com.example.zzalu.TitleHakwon.ComentRepository;
import com.example.zzalu.TitleHakwon.model.TitleHakwonComent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coment")
public class TitleHakwonComentController {


    private final ComentRepository comentRepository;

    @GetMapping("/{comentId}")

    public TitleHakwonComent  getTitleHakwonComent(@PathVariable Long id){

        System.out.println("[찾고 싶은 댓글의 id 값]"+id);
        return comentRepository.findTitleHakwonComent(id);
    }


    /**
     * redis에 댓글 저장하기
     * @param titleHakwonComent
     * @return
     */
    @PostMapping
    public TitleHakwonComent createTitleHakwonComentWithRedis (@RequestBody TitleHakwonComent titleHakwonComent){

           return comentRepository.createTitleHakwonComent(titleHakwonComent);
    }

}
