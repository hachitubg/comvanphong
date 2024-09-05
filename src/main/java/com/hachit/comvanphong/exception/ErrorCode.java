package com.hachit.comvanphong.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND("USER_NOT_FOUND", "User not found"),
    INVALID_CREDENTIALS("INVALID_CREDENTIALS", "Invalid username or password"),
    ACCESS_DENIED("ACCESS_DENIED", "You do not have permission to access this resource"),
    EMAIL_ALREADY_REGISTERED("EMAIL_ALREADY_REGISTERED", "Email is already registered"),
    PHONE_ALREADY_REGISTERED("PHONE_ALREADY_REGISTERED", "Phone number is already registered"),
    PASSWORD_TOO_SHORT("PASSWORD_TOO_SHORT", "Password must be at least 6 characters long"),
    PASSWORD_TOO_LONG("PASSWORD_TOO_LONG", "Password must be no more than 100 characters long"),
    FULL_NAME_TOO_LONG("FULL_NAME_TOO_LONG", "Full name must be no more than 200 characters long"),
    ADDRESS_TOO_LONG("ADDRESS_TOO_LONG", "Office address must be no more than 255 characters long"),
    EMAIL_TOO_LONG("EMAIL_TOO_LONG", "Email must be no more than 100 characters long"),
    PHONE_TOO_LONG("PHONE_TOO_LONG", "Phone number must be no more than 20 characters long"),
    INVALID_EMAIL_FORMAT("INVALID_EMAIL_FORMAT", "Invalid email format"),
    INVALID_PHONE_FORMAT("INVALID_PHONE_FORMAT", "Invalid phone number format");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
