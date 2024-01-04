package com.library.tijoLibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookListController {

    @GetMapping("/book-list")
    public String bookList() {
        return "book-list";
    }
}
