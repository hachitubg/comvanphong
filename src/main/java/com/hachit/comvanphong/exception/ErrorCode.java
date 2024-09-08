package com.hachit.comvanphong.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found"),
    INVALID_CREDENTIALS("INVALID_CREDENTIALS", "Invalid username or password"),
    ACCESS_DENIED("ACCESS_DENIED", "You do not have permission to access this resource"),

    // Đăng ký tài khoản
    EMAIL_ALREADY_REGISTERED("EMAIL_ALREADY_REGISTERED", "Email is already registered"),
    PHONE_ALREADY_REGISTERED("PHONE_ALREADY_REGISTERED", "Phone number is already registered");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
