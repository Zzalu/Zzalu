package com.samsamoo.zzalu.User.repository;

import com.samsamoo.zzalu.User.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findMemberByMemberId (String memberId);
}
