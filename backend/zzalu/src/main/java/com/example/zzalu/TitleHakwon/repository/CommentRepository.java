package com.example.zzalu.TitleHakwon.repository;

import com.example.zzalu.TitleHakwon.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

    Comment findById(Long id);
    Page<Comment> findByIdLessThanAndTitleHakwonIdOrderByIdDesc(Long lastCommentId, Long titleHackwonId , PageRequest pageRequest);
}
