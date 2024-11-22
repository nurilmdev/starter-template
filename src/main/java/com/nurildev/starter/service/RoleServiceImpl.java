package com.nurildev.starter.service;

import com.nurildev.starter.entity.Role;
import com.nurildev.starter.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepo roleRepo;
    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public Role getById(Long id) {
        return roleRepo.findById(id).orElseThrow(()->new UsernameNotFoundException("Role not found!"));
    }
}
