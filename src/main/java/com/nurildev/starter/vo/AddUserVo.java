package com.nurildev.starter.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserVo {

    private String name;
    private String email;
    private String username;
    private String password;
    private Long roleId;
}
