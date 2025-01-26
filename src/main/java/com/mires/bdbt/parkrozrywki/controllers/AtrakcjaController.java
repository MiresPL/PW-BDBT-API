package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.services.AtrakcjaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/atrakcje")
public class AtrakcjaController {
    private final AtrakcjaService atrakcjaService;

    @Autowired
    public AtrakcjaController(AtrakcjaService atrakcjaService) {
        this.atrakcjaService = atrakcjaService;
    }

    @GetMapping
    public String listAtrakcje(Model model, HttpServletRequest request) {
        model.addAttribute("atrakcjeList", atrakcjaService.findAll());
        model.addAttribute("request", request);
        return "attractions/attractions";
    }
}