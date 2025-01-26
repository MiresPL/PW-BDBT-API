package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.Adres;
import com.mires.bdbt.parkrozrywki.entities.Pracownik;
import com.mires.bdbt.parkrozrywki.entities.PracownikAdresWrapper;
import com.mires.bdbt.parkrozrywki.services.AdressService;
import com.mires.bdbt.parkrozrywki.services.PocztaService;
import com.mires.bdbt.parkrozrywki.services.PracownicyService;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pracownicy")
public class PracownicyController {
    private final PracownicyService pracownicyService;
    private final AdressService adressService;
    private final PocztaService pocztaService;

    public PracownicyController(PracownicyService pracownicyService, AdressService adressService, PocztaService pocztaService) {
        this.pracownicyService = pracownicyService;
        this.adressService = adressService;
        this.pocztaService = pocztaService;
    }

    @GetMapping
    public String getAllPracownicy(final Model model) {
        model.addAttribute("pracownicy", pracownicyService.getAllPracownicy());
        return "pracownik/pracownicy";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Pracownik pracownik = pracownicyService.findById(id);

        Hibernate.initialize(pracownik.getAdres());

        System.out.println(pracownik.getAdres().getNrAdresu());
        PracownikAdresWrapper pracownikAdresWrapper =  new PracownikAdresWrapper(pracownik, pracownik.getAdres());
        model.addAttribute("pracownikAdresWrapper", pracownikAdresWrapper);
        return "pracownicy/Update";
    }

    @GetMapping("/addNew")
    public String showNewPracownikForm(Model model) {
        PracownikAdresWrapper pracownikAdresWrapper =  new PracownikAdresWrapper(new Pracownik(), new Adres());
        model.addAttribute("pracownikAdresWrapper", pracownikAdresWrapper);
        model.addAttribute("poczty", pocztaService.findAll());
        return "pracownicy/AddNew";
    }

    @PostMapping("/add")
    public String savePracownik(@ModelAttribute("pracownikAdresWrapper") PracownikAdresWrapper pracownikAdresWrapper) {
        Adres adres = pracownikAdresWrapper.getAdres();
        Pracownik pracownik = pracownikAdresWrapper.getPracownik();

        if (adres != null) {
            if (adres.getNrAdresu() != null) {
                Adres existingAdres = adressService.findById(adres.getNrAdresu());
                for (int i = 0; i < 10; i++) System.out.println("ADRESS");

                if (existingAdres != null) {
                    existingAdres.setMiejscowosc(adres.getMiejscowosc());
                    existingAdres.setUlica(adres.getUlica());
                    existingAdres.setNrDomu(adres.getNrDomu());
                    existingAdres.setNrLokalu(adres.getNrLokalu());
                    existingAdres.setPoczta(adres.getPoczta());

                    adressService.save(existingAdres);
                    pracownik.setAdres(existingAdres);
                } else {
                    adres.setNrAdresu(adressService.getNextId());
                    adressService.save(adres);
                    pracownik.setAdres(adres);
                }
            } else {
                adres.setNrAdresu(adressService.getNextId());
                adressService.save(adres);
                pracownik.setNrPracownika(pracownicyService.getNextId());
                pracownik.setNrParku(1L);
                pracownik.setAdres(adres);
            }
        }

        // Save or update Pracownik
        pracownicyService.save(pracownik);
        return "redirect:/admin";
    }

    @GetMapping("/remove/{id}")
    public String removePracownik(@PathVariable Long id) {
        final Pracownik pracownik = pracownicyService.findById(id);
        final Long adresId = pracownik.getAdres().getNrAdresu();
        pracownicyService.remove(id);
        adressService.remove(adresId);
        return "redirect:/admin";
    }
}
