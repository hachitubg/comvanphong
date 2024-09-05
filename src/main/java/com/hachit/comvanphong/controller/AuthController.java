package com.hachit.comvanphong.controller;

import com.hachit.comvanphong.dto.LoginRequest;
import com.hachit.comvanphong.entity.User;
import com.hachit.comvanphong.exception.ApiException;
import com.hachit.comvanphong.response.ApiResponse;
import com.hachit.comvanphong.response.ResponseCode;
import com.hachit.comvanphong.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(summary = "API đăng ký người dùng mới")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(ApiResponse.success("User registered successfully"));
        } catch (ApiException e) {
            return ResponseEntity.status(400).body(ApiResponse.failure(e.getErrorCode().getCode(), e.getErrorCode().getMessage()));
        }
    }

    @Operation(summary = "API đăng nhập")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.findByPhoneNumber(loginRequest.getPhoneNumber());

        if (user.isPresent() && userService.checkPassword(loginRequest.getPassword(), user.get().getPassword())) {
            // Phản hồi khi đăng nhập thành công
            return ResponseEntity.ok(ApiResponse.success("Login successful"));
        } else {
            // Phản hồi nếu thông tin đăng nhập không đúng
            return ResponseEntity.status(401).body(ApiResponse.failure(ResponseCode.LOGIN_FAILURE));
        }
    }
}
