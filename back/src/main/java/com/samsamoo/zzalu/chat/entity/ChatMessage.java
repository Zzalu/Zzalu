package com.samsamoo.zzalu.chat.entity;

import com.samsamoo.zzalu.chat.dto.ChatMessageDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAT_MESSAGE_ID")
    private Long id;

    @Column(name = "TYPE")
    private ChatMessageDto.MessageType type;

    @Column(name = "ROOM_ID")
    private String roomId;

    @Column(name = "SENDER")
    private String sender;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "SEMD_DATE")
    private LocalDateTime sendDate;

    @ManyToOne
    @JoinColumn(name = "CHAT_ROOM_ID")
    private ChatRoom chatRoom;

    public void setChatRoom(ChatRoom chatRoom){
        if(this.chatRoom != null) {
            this.chatRoom.getChatMessages().remove(this);
        }
        this.chatRoom = chatRoom;
        if(!chatRoom.getChatMessages().contains(this)) {
            chatRoom.getChatMessages().add(this);
        }
    }

}
