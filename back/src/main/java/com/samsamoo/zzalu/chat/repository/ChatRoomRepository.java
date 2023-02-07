package com.samsamoo.zzalu.chat.repository;

import com.samsamoo.zzalu.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
