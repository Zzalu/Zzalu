package com.samsamoo.zzalu.TitleHakwon.repository;

import com.samsamoo.zzalu.TitleHakwon.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike,String> {
    boolean existsByComment_IdAndMemberUsername(Long commentId, String memberId);

    void deleteByComment_IdAndMemberUsername(Long commentId, String memberId);
}
