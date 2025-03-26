package com.example.mikroServiceUser.repository;

import com.example.mikroServiceUser.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTelegramId(String telegramId);

    boolean existsByTelegramId(String telegramId);
}
