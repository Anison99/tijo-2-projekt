package com.library.tijoLibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddBookController {

    @GetMapping("/add-book")
    public String addBook() {
        return "add-book";
    }
}

