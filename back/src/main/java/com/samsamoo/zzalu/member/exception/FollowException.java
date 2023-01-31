package com.samsamoo.zzalu.member.exception;

import com.samsamoo.zzalu.advice.BadRequestException;

public class FollowException extends BadRequestException {
    public FollowException(String message) {
        super(message);
    }

}
