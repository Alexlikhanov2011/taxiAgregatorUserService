package com.example.mikroServiceUser.service;

import com.example.mikroServiceUser.model.User;
import com.example.mikroServiceUser.model.UserStatus;
import com.example.mikroServiceUser.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String telegramId, String firstName, String lastName, String phoneNumber) {
        if (userRepository.existsByTelegramId(telegramId)) {
            throw new IllegalArgumentException("User already exists");
        }

        User user = new User();
        user.setTelegramId(telegramId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setRoles(Set.of("USER"));
        user.setStatus(UserStatus.ACTIVE);

        return (User) userRepository.save(user);
    }
    public User getUserByTelegramId(String telegramId) {
        return userRepository.findByTelegramId(telegramId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void updateUserPhone(String telegramId, String phoneNumber) {
        User user = getUserByTelegramId(telegramId);
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }
}
