package com.nurildev.starter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
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
