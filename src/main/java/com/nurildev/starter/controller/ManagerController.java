package com.nurildev.starter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        model.addAttribute("userLogin", principal.getName());
        return "home";
    }
}
