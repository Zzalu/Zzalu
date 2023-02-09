package com.samsamoo.zzalu.chat.repository;

import com.samsamoo.zzalu.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByRoomId(String roomId);
    List<ChatRoom> findTop10ByOrderByLikeCountDesc();
    List<ChatRoom> findByTagsContainsOrRoomNameContains(String keyword1, String keyword2);
    List<ChatRoom> findByTagsContains(String keyword1);
    List<ChatRoom> findByRoomNameContains(String keyword1);
    List<ChatRoom> findAllByMemberId(Long memberId);
    List<ChatRoom> findAllByMemberIdOrderByLastActivationDesc(Long memberId);
    List<ChatRoom> findAllByMemberIdOrderByLikeCountDesc(Long memberId);
    List<ChatRoom> findAllByTagsContainsOrRoomNameContainsOrderByLastActivationDesc(String keyword1, String keyword2);
    List<ChatRoom> findAllByTagsContainsOrRoomNameContainsOrderByLikeCount(String keyword1, String keyword2);
    List<ChatRoom> findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLastActivationDesc(Long memberId, String keyword1, String keyword2);
    List<ChatRoom> findAllByMemberIdAndTagsContainsOrRoomNameContainsOrderByLikeCountDesc(Long memberId, String keyword1, String keyword2);

}
