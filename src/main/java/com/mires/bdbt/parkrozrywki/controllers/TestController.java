package com.mires.bdbt.parkrozrywki.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@CrossOrigin(value = "*", origins = "*")
public class TestController {
    @GetMapping("")
    public String home(final Model model) {
        model.addAttribute("message", "Hello World!");
        return "home/Home";
    }
}
