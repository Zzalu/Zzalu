package com.samsamoo.zzalu.TitleHakwon.repository;


import com.samsamoo.zzalu.TitleHakwon.entity.ReplyComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyCommentRepository extends JpaRepository<ReplyComment, String> {


    ReplyComment findById(Long id);
    Page<ReplyComment> findByIdLessThanAndParentCommentIdOrderByIdDesc(Long lastReplyCommentId , Long parentCommentId , PageRequest pageRequest);
    Page<ReplyComment> findByIdGreaterThanAndParentCommentId(Long lastReplyCommentId , Long parentCommentId , PageRequest pageRequest);
    void     deleteById (Long id);
}
