package com.mires.bdbt.parkrozrywki.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DefaultController {
    @GetMapping("")
    public String home(final Model model) {
        return "home/Home";
    }
}
