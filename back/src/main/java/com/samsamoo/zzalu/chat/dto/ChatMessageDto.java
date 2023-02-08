package com.samsamoo.zzalu.chat.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDto implements Serializable {
    public enum MessageType {
        ENTER, JOIN, TALK, @JsonEnumDefaultValue UNKNOWN;
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private LocalDateTime sendDate;

}
