package com.samsamoo.zzalu.member.exception;

import com.samsamoo.zzalu.advice.BadRequestException;

public class NotMatchException extends BadRequestException {
    public NotMatchException(String message) {
        super(message);
    }

}
