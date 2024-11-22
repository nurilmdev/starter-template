package com.nurildev.starter.repository;

import com.nurildev.starter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByNameIgnoreCaseContainsOrUsernameIgnoreCaseContains(String name, String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
