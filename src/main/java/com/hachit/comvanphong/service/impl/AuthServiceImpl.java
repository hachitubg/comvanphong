package com.hachit.comvanphong.service.impl;

import com.hachit.comvanphong.dto.UserDTO;
import com.hachit.comvanphong.entity.User;
import com.hachit.comvanphong.exception.DuplicateDataException;
import com.hachit.comvanphong.exception.InvalidCredentialsException;
import com.hachit.comvanphong.exception.ResourceNotFoundException;
import com.hachit.comvanphong.model.JwtResponse;
import com.hachit.comvanphong.model.LoginRequest;
import com.hachit.comvanphong.repository.UserRepository;
import com.hachit.comvanphong.security.JwtTokenProvider;
import com.hachit.comvanphong.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtTokenProvider jwtTokenProvider;

    // Constructor Injection
    public AuthServiceImpl(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void register(UserDTO userDTO) {
        // Mã hóa mật khẩu
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User userRegister = modelMapper.map(userDTO, User.class);

        // Kiểm tra xem email đã tồn tại chưa
        Optional<User> existingUserByEmail = userRepository.findByEmail(userRegister.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new DuplicateDataException("Email đã tồn tại: " + userRegister.getEmail());
        }

        // Kiểm tra xem số điện thoại đã tồn tại chưa
        Optional<User> existingUserByPhone = userRepository.findByPhoneNumber(userRegister.getPhoneNumber());
        if (existingUserByPhone.isPresent()) {
            throw new DuplicateDataException("Số điện thoại đã tồn tại" + userRegister.getPhoneNumber());
        }

        // Lưu người dùng vào cơ sở dữ liệu
        userRepository.save(userRegister);
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        // Kiểm tra nếu không tìm thấy người dùng
        User user = findByPhoneNumber(loginRequest.getPhoneNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng với số điện thoại: " + loginRequest.getPhoneNumber()));

        // Kiểm tra nếu mật khẩu không khớp
        if (!checkPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Sai mật khẩu !");
        }

        // Tạo JWT token
        String token = jwtTokenProvider.createToken(user.getPhoneNumber(), user.getRole());
        return new JwtResponse(token);
    }

    @Override
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}