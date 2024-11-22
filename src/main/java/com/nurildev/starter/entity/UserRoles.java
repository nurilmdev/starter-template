package com.nurildev.starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_roles")
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id",referencedColumnName = "id", nullable = false)
    private Role role;

    @Column(nullable = false, name = "created_by", columnDefinition = "default 'current_timestamp()'")
    private String createdBy;
    @Column(name = "creation_date")
    private Timestamp creationDate;
    @Column(name = "last_update_by")
    private String lastUpdateBy;
    @Column(name = "last_update_date")
    private Timestamp lastUpdateDate;
    @Column(name = "enabled_flag", columnDefinition = "default 'Y'")
    private String enabledFlag;
}
