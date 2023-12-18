package com.library.tijoLibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfileController {

    @GetMapping("/user-profile")
    public String userProfile() {
        return "user-profile";
    }
}
