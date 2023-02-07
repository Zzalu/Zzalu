package com.samsamoo.zzalu.TitleHakwon.repository;

import com.samsamoo.zzalu.TitleHakwon.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.listener.ChannelTopic;

import java.util.List;
import java.util.Map;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //제목학원의 redis Topic을 저장할 Map


    Page<Comment> findByIdLessThanAndTitleHakwonIdOrderByIdDesc(Long lastCommentId, Long titleHackwonId , PageRequest pageRequest);
    Page<Comment> findByIdGreaterThanAndTitleHakwonId (Long lastCommentId, Long titleHackwonId , PageRequest pageRequest);

    List<Comment> findTop50ByTitleHakwonIdAndLikeNumGreaterThanOrderByLikeNumDesc(Long titleHackwonId,int likesize);


    List<Comment> findTop3ByTitleHakwonIdAndLikeNumGreaterThanOrderByLikeNumDesc(Long titleHackwonId,int likesize);

}
