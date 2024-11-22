package com.nurildev.starter.controller;

import com.nurildev.starter.entity.Role;
import com.nurildev.starter.entity.User;
import com.nurildev.starter.entity.UserRoles;
import com.nurildev.starter.service.RoleService;
import com.nurildev.starter.service.UserRolesService;
import com.nurildev.starter.service.UserService;
import com.nurildev.starter.vo.AddUserVo;
import com.nurildev.starter.vo.SearchVo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;
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
    public String manageUser(Model model){
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("keyword", new SearchVo());
        return "/admin/manage-user";
    }
    @GetMapping("/add-user")
    public String addUser(Model model){

        List<Role> roles = roleService.getAllRoles().stream()
                .map(role -> {
                    Role entity = Role.builder()
                            .id(role.getId())
                            .name(role.getName().replaceAll("ROLE_", ""))
                            .build();
                            return entity;
                        }
                    )
                .collect(Collectors.toList());

        model.addAttribute("roles", roles);
        model.addAttribute("newUser", new AddUserVo());
        return "/admin/add-user";
    }

    @PostMapping("/save-user")
    public String submitUser(@Valid @ModelAttribute("newUser") AddUserVo addUserVo, BindingResult error, Principal principal, Model model){
        if (error.hasErrors()) {
            List<Role> roles = roleService.getAllRoles().stream()
                    .map(role -> {
                                Role entity = Role.builder()
                                        .id(role.getId())
                                        .name(role.getName().replaceAll("ROLE_", ""))
                                        .build();
                                return entity;
                            }
                    )
                    .collect(Collectors.toList());

            model.addAttribute("roles", roles);
            return "/admin/add-user";
        }
        List<Role> roles = addUserVo.getRoleId().stream()
                .map(roleId -> roleService.getById(roleId))
                .collect(Collectors.toList());

        User newUser = User.builder()
                .name(addUserVo.getName())
                .email(addUserVo.getEmail())
                .username(addUserVo.getUsername())
                .password(new BCryptPasswordEncoder().encode(addUserVo.getPassword()))

                .createdBy(principal.getName())
                .creationDate(new Timestamp(System.currentTimeMillis()))
                .enabledFlag("Y")
                .build();

        for (Role role: roles) {
            userService.save(newUser, role, principal.getName());
        }

        model.addAttribute("userList", userService.getAllUsers());
        return "redirect:manage-user";
    }

    @PostMapping("/search-user")
    public String search(SearchVo searchVo, Model model){
        model.addAttribute("keyword", searchVo);
        model.addAttribute("userList", userService.findUser(searchVo.getKeyword()));
        return "/admin/manage-user";
    }
}
