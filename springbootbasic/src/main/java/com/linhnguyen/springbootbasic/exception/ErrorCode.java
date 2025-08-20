package com.linhnguyen.springbootbasic.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "uncategorized exception"),
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXISTED(1002, "user existed"),
    USER_NOT_FOUD(1003, "User not found"),
    USERNAME_INVALID(1004, "Username must be at least 3 characters"),
    INVALID_PASSWORD(1005, "Password must be at least 8 characters"),

    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
