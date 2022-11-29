package me.ezra.core.global.exception;

import lombok.Getter;

@Getter
public class CalendarException extends RuntimeException {
    private final ErrorCode errorCode;

    public CalendarException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CalendarException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
