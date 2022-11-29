package me.ezra.core.global.exception;

public enum ErrorCode {

    // Member
    PASSWORD_NOT_MATCH(400, "M001", "비밀번호 불일치"),
    ALREADY_EXISTS_USER(400, "M002", "이미 있는 계정"),
    USER_NOT_FOUND(400, "M003", "존재하지 않는 계정"),

    // Common
    VALIDATION_FAIL(400, "C001", "값이 유효하지 않음"),
    BAD_REQUEST(400, "C002", "잘못된 접근"),
    EVENT_CREATE_OVERLAPPED_PERIOD(400, "C003", "이벤트 기간 중복");

    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
