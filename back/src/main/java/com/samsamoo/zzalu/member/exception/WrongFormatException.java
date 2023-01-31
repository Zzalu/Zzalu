package com.samsamoo.zzalu.member.exception;

import com.samsamoo.zzalu.advice.BadRequestException;

public class WrongFormatException extends BadRequestException {
    public WrongFormatException(String message) {
        super(message);
    }

}
