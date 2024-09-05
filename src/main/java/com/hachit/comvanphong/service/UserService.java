package com.hachit.comvanphong.service;

import com.hachit.comvanphong.entity.User;
import com.hachit.comvanphong.exception.ApiException;
import com.hachit.comvanphong.exception.ErrorCode;
import com.hachit.comvanphong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Pattern để kiểm tra định dạng email hợp lệ
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    // Pattern để kiểm tra định dạng số điện thoại (chỉ chấp nhận số và có thể có dấu +)
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]*$");

    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public User registerUser(User user) {
        // Kiểm tra độ dài tối đa của các trường
        if (user.getFullName().length() > 200) {
            throw new ApiException(ErrorCode.FULL_NAME_TOO_LONG);
        }

        if (user.getOfficeAddress().length() > 255) {
            throw new ApiException(ErrorCode.ADDRESS_TOO_LONG);
        }

        if (user.getEmail().length() > 100) {
            throw new ApiException(ErrorCode.EMAIL_TOO_LONG);
        }

        if (user.getPhoneNumber().length() > 20) {
            throw new ApiException(ErrorCode.PHONE_TOO_LONG);
        }

        if (user.getPassword().length() > 100) {
            throw new ApiException(ErrorCode.PASSWORD_TOO_LONG);
        }

        // Kiểm tra định dạng email
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new ApiException(ErrorCode.INVALID_EMAIL_FORMAT);
        }

        // Kiểm tra định dạng số điện thoại
        if (!PHONE_PATTERN.matcher(user.getPhoneNumber()).matches()) {
            throw new ApiException(ErrorCode.INVALID_PHONE_FORMAT);
        }

        // Kiểm tra xem email đã tồn tại chưa
        Optional<User> existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new ApiException(ErrorCode.EMAIL_ALREADY_REGISTERED);
        }

        // Kiểm tra xem số điện thoại đã tồn tại chưa
        Optional<User> existingUserByPhone = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (existingUserByPhone.isPresent()) {
            throw new ApiException(ErrorCode.PHONE_ALREADY_REGISTERED);
        }

        // Kiểm tra mật khẩu độ dài tối thiểu
        if (user.getPassword().length() < 6) {
            throw new ApiException(ErrorCode.PASSWORD_TOO_SHORT);
        }

        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Thiết lập role mặc định là USER nếu chưa có
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }

        // Lưu người dùng vào cơ sở dữ liệu
        return userRepository.save(user);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        // So sánh mật khẩu đã mã hóa
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
