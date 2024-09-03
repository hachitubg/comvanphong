package com.hachit.comvanphong.service;

import com.hachit.comvanphong.entity.User;
import com.hachit.comvanphong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
