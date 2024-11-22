package com.nurildev.starter.repository;

import com.nurildev.starter.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
