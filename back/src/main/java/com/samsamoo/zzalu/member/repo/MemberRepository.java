package com.samsamoo.zzalu.member.repo;

import com.samsamoo.zzalu.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsMemberByNickname(String nickname);
    boolean existsMemberByUsername(String username);
    boolean existsMemberByUserEmail(String email);
    Optional<Member> findByUsername(String username);

    Optional<Member> findByMemberId(Long id);
}
