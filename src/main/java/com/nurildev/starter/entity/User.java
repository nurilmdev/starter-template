package com.nurildev.starter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
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
    @Column(unique = false, nullable = false)
    private String role;
    @Column(unique = false, nullable = false, name = "created_by")
    private String createdBy;
    @Column(unique = false, nullable = false, name = "creation_date")
    private Timestamp creationDate;
    @Column(unique = false, name = "last_update_by")
    private String lastUpdateBy;
    @Column(unique = false, name = "last_update_date")
    private Timestamp lastUpdateDate;
    @Column(unique = false, name = "enabled_flag")
    private String enabledFlag;
}
