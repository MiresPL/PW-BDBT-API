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
    
    @GetMapping("/login")
    public String login(final Model model) {
        model.addAttribute("message", "Please log in using your credentials.");
        return "login/Login";
    }

    @GetMapping("/atrakcje")
    public String attractions(final Model model) {
        model.addAttribute("message", "Explore our amazing attractions.");
        return "attractions/Attractions";
    }

    @GetMapping("/bilety")
    public String tickets(final Model model) {
        model.addAttribute("message", "Browse our ticketing options.");
        return "tickets/Tickets";
    }
    
    @GetMapping("/profil")
    public String profile(final Model model) {
        model.addAttribute("message", "View and update your profile.");
        return "profile/Profile";
    }
    
    @GetMapping("/admin")
    public String admin(final Model model) {
        model.addAttribute("message", "Admin dashboard and tools.");
        return "admin/Admin";
    }
    
    
}
