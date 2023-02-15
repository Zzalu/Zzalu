package com.samsamoo.zzalu.domain.titlehakwon.repository;

import com.samsamoo.zzalu.domain.titlehakwon.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //제목학원의 redis Topic을 저장할 Map


    Page<Comment> findByIdLessThanAndTitleHakwonIdOrderByIdDesc(Long lastCommentId, Long titleHackwonId , PageRequest pageRequest);
    Page<Comment> findByIdGreaterThanAndTitleHakwonId (Long lastCommentId, Long titleHackwonId , PageRequest pageRequest);

    List<Comment> findTop50ByTitleHakwonIdAndLikeNumGreaterThanOrderByLikeNumDesc(Long titleHackwonId,int likesize);


    List<Comment> findTop3ByTitleHakwonIdAndLikeNumGreaterThanOrderByLikeNumDesc(Long titleHackwonId,int likesize);

}
