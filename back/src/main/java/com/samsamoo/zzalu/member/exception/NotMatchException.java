package com.samsamoo.zzalu.member.exception;

import com.samsamoo.zzalu.global.advice.BadRequestException;

public class NotMatchException extends BadRequestException {
    public NotMatchException(String message) {
        super(message);
    }

}
