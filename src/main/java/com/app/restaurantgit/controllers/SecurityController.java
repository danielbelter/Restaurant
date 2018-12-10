package com.app.restaurantgit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class SecurityController {
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("error", "");
        return "security/loginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("error", "Nieprawidłowe dane logowania");
        return "security/loginForm";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        model.addAttribute("error", "Nie masz uprawnien do tej strony");
        return "security/accessDeniedPage";
    }
}
