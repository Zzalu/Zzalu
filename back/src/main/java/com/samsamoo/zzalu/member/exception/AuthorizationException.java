package com.samsamoo.zzalu.member.exception;

import com.samsamoo.zzalu.advice.ForbiddenException;

public class AuthorizationException extends ForbiddenException {
    private static final String MESSAGE = "권한이 없습니다.";

    public AuthorizationException() {
        super(MESSAGE);
    }

    public AuthorizationException(String message) {
        super(message);
    }
}
