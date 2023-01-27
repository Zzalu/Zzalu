package com.example.zzalu.TitleHakwon.controller;

import com.example.zzalu.TitleHakwon.service.ComentService;
import com.example.zzalu.TitleHakwon.entity.Coment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.zzalu.TitleHakwon.dto.RequestComent;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coment")
public class ComentController {


    private final ComentService comentService;


    /**
     * 댓글 저장하기
     */
    @PostMapping
    public Coment addComent(@RequestBody RequestComent requestComent){



        System.out.println(requestComent.getMemberId());
        return comentService.addComent(requestComent);

    }

    /**
     * 대댓글 저장하기
     *
     */
    @PostMapping
    public Coment addReplyComent(@RequestBody RequestComent requestComent){


     //   comentService.ad
        System.out.println(requestComent.getMemberId());
        return comentService.addComent(requestComent);

    }
}
