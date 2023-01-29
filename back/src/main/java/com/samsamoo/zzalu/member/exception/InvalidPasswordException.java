package com.samsamoo.zzalu.member.exception;

import com.samsamoo.zzalu.advice.BadRequestException;

public class InvalidPasswordException extends BadRequestException {
    private static final String MESSAGE = "회원 정보가 정확하지 않습니다.";

    public InvalidPasswordException() {
        super(MESSAGE);
    }
}
