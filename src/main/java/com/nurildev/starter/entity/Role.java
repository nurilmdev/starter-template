package com.nurildev.starter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mst_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

//    @Column(nullable = false, unique = true, name = "role_code")
//    private String roleCode;

    @Column(nullable = false)
    private String name;

    // Getters and setters
}

