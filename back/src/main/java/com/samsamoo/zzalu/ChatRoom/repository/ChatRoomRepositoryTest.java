package com.samsamoo.zzalu.ChatRoom.repository;

import com.samsamoo.zzalu.ChatRoom.entity.ChatRoomTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ChatRoomRepositoryTest extends JpaRepository<ChatRoomTest, Long> {
    Optional<ChatRoomTest> findByRoomNumber(String roomNumber);
    List<ChatRoomTest> findTop10ByOrderByLikeCountDesc();
}
