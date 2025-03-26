package com.example.mikroServiceUser.controller;

import com.example.mikroServiceUser.model.User;
import com.example.mikroServiceUser.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(
            @RequestParam String telegramId,
            @RequestParam String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam String phoneNumber) {
        return userService.registerUser(telegramId, firstName, lastName, phoneNumber);
    }

    @GetMapping("/{telegramId}")
    public User getUser(@PathVariable String telegramId) {
        return userService.getUserByTelegramId(telegramId);
    }

    @PutMapping("/{telegramId}/phone")
    public User updatePhone(@PathVariable String telegramId, @RequestParam String phoneNumber) {
        userService.updateUserPhone(telegramId, phoneNumber);
        return userService.getUserByTelegramId(telegramId);
    }
}
