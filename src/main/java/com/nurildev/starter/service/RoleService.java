package com.nurildev.starter.service;

import com.nurildev.starter.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getAllRoles();
    Role getById(Long id);
}
