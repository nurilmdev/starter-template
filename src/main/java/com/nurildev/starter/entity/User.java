package com.nurildev.starter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "mst_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = false, nullable = false)
    private String username;

    @Column(unique = false, nullable = false)
    private String password;

    @Column(unique = false, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRoles> userRoles = new ArrayList<>();

    @Column(unique = false, nullable = false, name = "created_by")
    private String createdBy;

    @Column(unique = false, name = "creation_date")
    private Timestamp creationDate;

    @Column(unique = false, name = "last_update_by")
    private String lastUpdateBy;

    @Column(unique = false, name = "last_update_date")
    private Timestamp lastUpdateDate;

    @Column(unique = false, name = "enabled_flag")
    private String enabledFlag;
}
