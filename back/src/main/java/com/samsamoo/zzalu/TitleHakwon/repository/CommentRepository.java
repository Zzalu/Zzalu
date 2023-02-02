package com.samsamoo.zzalu.TitleHakwon.repository;

import com.samsamoo.zzalu.TitleHakwon.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    Page<Comment> findByIdLessThanAndTitleHakwonIdOrderByIdDesc(Long lastCommentId, Long titleHackwonId , PageRequest pageRequest);




}
