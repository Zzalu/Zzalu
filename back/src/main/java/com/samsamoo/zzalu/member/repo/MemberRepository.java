package com.samsamoo.zzalu.member.repo;

import com.samsamoo.zzalu.chat.entity.ChatRoom;
import com.samsamoo.zzalu.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsMemberByNickname(String nickname);
    boolean existsMemberByUsername(String username);
    boolean existsMemberByUserEmail(String email);
    Optional<Member> findByUsername(String username);

    Optional<Member> findByUserEmail(String userEmail);

    Optional<Member> findById(Long id);

    List<ChatRoom> findAllByIdAndTagsContainsOrRoomNameContainsOrderByLastActivationDesc(Long id, String keyword1, String keyword2);
    List<ChatRoom> findAllByIdAndTagsContainsOrRoomNameContainsOrderByLikeCountDesc(Long id, String keyword1, String keyword2);


}
