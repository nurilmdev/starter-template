package com.nurildev.starter.service;

import com.nurildev.starter.entity.Role;
import com.nurildev.starter.entity.User;
import com.nurildev.starter.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public void save(User user) {
        userRepo.save(user);
    }
}
