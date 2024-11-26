package com.nurildev.starter.service;

import com.nurildev.starter.entity.Role;
import com.nurildev.starter.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void save(User user, List<Long> roleIds, String createdBy);
    List<User> getAllUsers();

    List<User> findUser(String keyword);
}
