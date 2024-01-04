package com.library.tijoLibrary.controllers;

import com.library.tijoLibrary.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    @GetMapping("/loginSuccess")
    public String loginSuccess() {
        return "redirect:/user-profile";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "redirect:/login?error=true";
    }
}
