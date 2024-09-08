package com.hachit.comvanphong.service;

import com.hachit.comvanphong.dto.RegisterUserDTO;
import com.hachit.comvanphong.entity.User;
import com.hachit.comvanphong.exception.ApiException;
import com.hachit.comvanphong.exception.ErrorCode;
import com.hachit.comvanphong.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    // Constructor Injection
    public UserService(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public void registerUser(RegisterUserDTO registerUserDTO) {
        // Mã hóa mật khẩu
        registerUserDTO.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        User userRegister = modelMapper.map(registerUserDTO, User.class);

        // Kiểm tra xem email đã tồn tại chưa
        Optional<User> existingUserByEmail = userRepository.findByEmail(userRegister.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new ApiException(ErrorCode.EMAIL_ALREADY_REGISTERED);
        }

        // Kiểm tra xem số điện thoại đã tồn tại chưa
        Optional<User> existingUserByPhone = userRepository.findByPhoneNumber(userRegister.getPhoneNumber());
        if (existingUserByPhone.isPresent()) {
            throw new ApiException(ErrorCode.PHONE_ALREADY_REGISTERED);
        }

        // Lưu người dùng vào cơ sở dữ liệu
        userRepository.save(userRegister);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        // So sánh mật khẩu đã mã hóa
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
