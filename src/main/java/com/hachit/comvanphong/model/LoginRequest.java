package com.hachit.comvanphong.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    private String phoneNumber;
    private String password;
}
