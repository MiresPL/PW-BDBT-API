package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.BiletyKlienci;
import com.mires.bdbt.parkrozrywki.services.BiletyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bilety")
public class BiletyController {
    private final BiletyService biletyService;

    public BiletyController(BiletyService biletyService) {
        this.biletyService = biletyService;
    }

    @GetMapping
    public String tickets(final Model model) {
        model.addAttribute("bilety", biletyService.findAll());
        return "tickets/Tickets";
    }

    @GetMapping("/buy")
    public String buyTicketForm(Model model) {
        model.addAttribute("biletyKlienci", new BiletyKlienci());
        return "tickets/Tickets";
    }
}
