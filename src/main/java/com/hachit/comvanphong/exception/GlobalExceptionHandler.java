package com.hachit.comvanphong.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý ngoại lệ khi tài nguyên không được tìm thấy
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // Xử lý ngoại lệ dữ liệu trùng lặp
    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<?> handleDuplicateDataException(DuplicateDataException ex) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage(), "Dữ liệu đã tồn tại");
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    // Xử lý ngoại lệ dữ liệu không hợp lệ
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<?> handleInvalidDataException(InvalidDataException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), "Dữ liệu không hợp lệ");
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // Xử lý ngoại lệ khi đăng nhập sai thông tin
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage(), "Thông tin đăng nhập không chính xác");
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    // Xử lý ngoại lệ chung
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
