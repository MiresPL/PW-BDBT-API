package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.Pracownik;
import com.mires.bdbt.parkrozrywki.services.PracownicyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pracownicy")
public class PracownicyController {
    private final PracownicyService pracownicyService;

    public PracownicyController(PracownicyService pracownicyService) {
        this.pracownicyService = pracownicyService;
    }

    @GetMapping
    public String getAllPracownicy(final Model model) {
        model.addAttribute("pracownicy", pracownicyService.getAllPracownicy());
        return "pracownik/pracownicy";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Pracownik pracownik = pracownicyService.findById(id); // Fetch the object from the database
        model.addAttribute("pracownik", pracownik);
        return "pracownik/add";
    }

    @PostMapping("/add")
    public String savePracownik(@ModelAttribute("pracownik") Pracownik pracownik) {
        pracownicyService.save(pracownik); // Save or update the object in the database
        return "redirect:/pracownik/list"; // Redirect to the list view after saving
    }

    @GetMapping("/remove/{id}")
    public String removePracownik(@PathVariable Long id) {
        pracownicyService.remove(id); // Remove the object from the database
        return "redirect:/pracownik/list"; // Redirect to the list view after removing
    }
}
