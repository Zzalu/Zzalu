package com.example.zzalu.User.repository;

import com.example.zzalu.User.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberByMemberId (String memberId);
}
