package com.nurildev.starter.repository;

import com.nurildev.starter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserAuthRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    @Query(value = """
            select usr_role.user_id,role.id,role.name 
            from user_roles usr_role
            join role role on role.id=usr_role.role_id 
            where usr_role.user_id=?1
            """,
            nativeQuery = true)
    List<Object[]> findRolesByUserId(Long userId);
}
