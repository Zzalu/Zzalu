package com.samsamoo.zzalu.TitleHakwon.exception;

import com.samsamoo.zzalu.advice.NotFoundException;

public class TitleHakwonException extends NotFoundException {
    private static final String MESSAGE = "해당 제목학원이 존재하지 않습니다.";

    public TitleHakwonException() {
        super(MESSAGE);
    }
    public TitleHakwonException(String message) {
        super(message);
    }
}
