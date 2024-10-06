package com.hachit.comvanphong.service;

import com.hachit.comvanphong.dto.UserDTO;
import com.hachit.comvanphong.entity.User;
import com.hachit.comvanphong.model.JwtResponse;
import com.hachit.comvanphong.model.LoginRequest;

import java.util.Optional;

public interface AuthService {

    Optional<User> findByPhoneNumber(String phoneNumber);

    void register(UserDTO userDTO);

    JwtResponse login(LoginRequest loginRequest);

    boolean checkPassword(String rawPassword, String encodedPassword);
}
