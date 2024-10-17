package com.nurildev.starter.controller;

import com.nurildev.starter.entity.Role;
import com.nurildev.starter.entity.User;
import com.nurildev.starter.service.RoleService;
import com.nurildev.starter.service.UserService;
import com.nurildev.starter.vo.AddUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @GetMapping("/")
    public String home(Principal principal, Model model){
        model.addAttribute("userLogin", principal.getName());
        return "/admin/admin";
    }
    @GetMapping("/manage-user")
    public String manageUser(Principal principal, Model model){
        model.addAttribute("userLogin", principal.getName());
        return "/admin/manage-user";
    }
    @GetMapping("/add-user")
    public String addUser(Model model){

        List<Role> roles = roleService.getAllRoles().stream()
                .map(role -> new Role(role.getRole_id(), role.getName().replaceAll("ROLE_", "")))
                .collect(Collectors.toList());

        model.addAttribute("roles", roles);
        model.addAttribute("newUser", new AddUserVo());
        return "/admin/add-user";
    }

    @PostMapping("/save-user")
    public String submitUser(AddUserVo addUserVo, Principal principal){
        Role role = roleService.getById(addUserVo.getRoleId());
        Set<Role> newRole = new HashSet<>();
        newRole.add(role);
        User newUser = User.builder()
                .name(addUserVo.getName())
                .email(addUserVo.getEmail())
                .username(addUserVo.getUsername())
                .password(new BCryptPasswordEncoder().encode(addUserVo.getPassword()))
                .roles(newRole)
                .createdBy(principal.getName())
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .build();
        userService.save(newUser);
        return "redirect:manage-user";
    }
}
