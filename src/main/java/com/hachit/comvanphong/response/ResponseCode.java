package com.hachit.comvanphong.response;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS("200", "Request was successful"),
    LOGIN_SUCCESS("200", "Login successful"),

    PHONE_NUMBER_ALREADY("400","Phone number already registered"),
    REGISTER_FAILURE("400", "Invalid credentials"),
    LOGIN_FAILURE("401", "Invalid credentials"),
    USER_NOT_FOUND("404", "User not found"),

    INTERNAL_SERVER_ERROR("500", "Internal server error");


    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
