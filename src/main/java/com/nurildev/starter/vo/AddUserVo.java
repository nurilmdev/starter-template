package com.nurildev.starter.vo;

import com.nurildev.starter.validation.UniqueEmail;
import com.nurildev.starter.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserVo {

    @NotNull @NotEmpty(message = "Nama tidak boleh kosong")
    @Length(min = 3, max = 50, message = "Minimal 3 dan Maksimal 50 char")
    private String name;

    @NotNull @NotEmpty(message = "Email tidak boleh kosong")
    @Email(message = "Format email tidak sesuai")
    @UniqueEmail
    private String email;

    @NotNull @NotEmpty(message = "Username tidak boleh kosong")
    @UniqueUsername
    private String username;

    @NotNull @NotEmpty(message = "Password tidak boleh kosong")
    private String password;

    @NotEmpty(message = "Harus pilih salah satu role")
    private List<Long> roleId;
}
