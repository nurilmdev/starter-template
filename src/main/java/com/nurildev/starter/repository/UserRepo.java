package com.nurildev.starter.repository;

import com.nurildev.starter.entity.Role;
import com.nurildev.starter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {
//    Set<Role> findAllRoles();
}
