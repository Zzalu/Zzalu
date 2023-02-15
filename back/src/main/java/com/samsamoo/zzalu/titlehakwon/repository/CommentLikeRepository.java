package com.samsamoo.zzalu.titlehakwon.repository;

import com.samsamoo.zzalu.titlehakwon.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike,String> {
    boolean existsByComment_IdAndMemberUsername(Long commentId, String username);

    void deleteByComment_IdAndMemberUsername(Long commentId, String username);
}
