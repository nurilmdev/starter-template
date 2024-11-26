package com.nurildev.starter.service;

import com.nurildev.starter.entity.Role;
import com.nurildev.starter.entity.User;
import com.nurildev.starter.entity.UserRoles;
import com.nurildev.starter.exception.RoleNotFoundException;
import com.nurildev.starter.repository.RoleRepo;
import com.nurildev.starter.repository.UserRepo;
import com.nurildev.starter.repository.UserRolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserRolesRepo userRolesRepo;

    @Override
    public void save(User user, List<Long> roleIds, String createdBy) {
        List<Role> roles = roleIds.stream()
            .map(roleId -> roleRepo.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role not found")))
            .collect(Collectors.toList());

        for (Role role:roles) {
            UserRoles userRoles = new UserRoles();
            userRoles.setRole(role);
            userRoles.setUser(user);
            userRoles.setCreatedBy(createdBy);
            userRoles.setCreationDate(new Timestamp(System.currentTimeMillis()));
            userRoles.setEnabledFlag("Y");
            userRolesRepo.save(userRoles);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findUser(String keyword) {
        System.out.println(keyword);
        return userRepo.findByNameIgnoreCaseContainsOrUsernameIgnoreCaseContains(keyword, keyword);
    }
}
