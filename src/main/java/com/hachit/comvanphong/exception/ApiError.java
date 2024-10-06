package com.hachit.comvanphong.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    private String message;
    private String details;
    private LocalDateTime timestamp;

    public ApiError(HttpStatus status, String message, String details) {
        super();
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();  // Lấy thời gian hiện tại
    }
}
