package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.BiletyKlienci;
import com.mires.bdbt.parkrozrywki.services.BiletyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/bilety")
@SessionAttributes("klient")
public class BiletyController {
    private final BiletyService biletyService;

    public BiletyController(BiletyService biletyService) {
        this.biletyService = biletyService;
    }

    @GetMapping
    public String tickets(final Model model, HttpServletRequest request) {
        model.addAttribute("biletyKlienci", new BiletyKlienci());
        model.addAttribute("bilety", biletyService.findAll());
        model.addAttribute("request", request);
        return "tickets/Tickets";
    }
}