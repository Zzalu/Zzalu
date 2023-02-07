package com.samsamoo.zzalu.chat.repository;

import com.samsamoo.zzalu.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

}
