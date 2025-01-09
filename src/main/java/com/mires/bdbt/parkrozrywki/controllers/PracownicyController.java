package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.Pracownik;
import com.mires.bdbt.parkrozrywki.services.PracownicyService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pracownicy")
public class PracownicyController {
    private final PracownicyService pracownicyService;

    public PracownicyController(PracownicyService pracownicyService) {
        this.pracownicyService = pracownicyService;
    }

    @GetMapping
    public List<Pracownik> getAllPracownicy(final Model model) {
        model.addAttribute("pracownicy", pracownicyService.getAllPracownicy());
        return pracownicyService.getAllPracownicy();
    }
}
