package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.Adres;
import com.mires.bdbt.parkrozrywki.entities.Pracownik;
import com.mires.bdbt.parkrozrywki.services.AdressService;
import com.mires.bdbt.parkrozrywki.services.PracownicyService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adress")
public class AdressController {
    private final AdressService adressService;

    public AdressController(AdressService adressService) {
        this.adressService = adressService;
    }

    @GetMapping
    public List<Adres> getAllAdresses(final Model model) {
        model.addAttribute("adresy", adressService.findAll());
        return adressService.findAll();
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Adres adres = adressService.findById(id);
        model.addAttribute("adress", adres);
        return "adress/add";
    }

    @PostMapping("/add")
    public String saveAdress(@ModelAttribute("adress") Adres adres) {
        adressService.save(adres);
        return "redirect:/adress/list";
    }

    @GetMapping("/remove/{id}")
    public String removeAdress(@PathVariable Long id) {
        adressService.remove(id);
        return "redirect:/adress/list";
    }
}
