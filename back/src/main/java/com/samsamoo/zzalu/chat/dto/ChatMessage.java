package com.samsamoo.zzalu.chat.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType {
        ENTER, JOIN, TALK, @JsonEnumDefaultValue UNKNOWN;
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

}
