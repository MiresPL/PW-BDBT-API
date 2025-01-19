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
        PracownikAdresWrapper pracownikAdresWrapper =  new PracownikAdresWrapper(pracownik, pracownik.getAdres()); // Fetch the object from the database
        model.addAttribute("pracownikAdresWrapper", pracownikAdresWrapper);
        return "pracownicy/Update";
    }

    @GetMapping("/addNew")
    public String showNewPracownikForm(Model model) {
        PracownikAdresWrapper pracownikAdresWrapper =  new PracownikAdresWrapper(new Pracownik(), new Adres()); // Fetch the object from the database
        model.addAttribute("pracownikAdresWrapper", pracownikAdresWrapper);
        model.addAttribute("poczty", pocztaService.findAll());
        return "pracownicy/AddNew";
    }

    @PostMapping("/add")
    public String savePracownik(@ModelAttribute("pracownikAdresWrapper") PracownikAdresWrapper pracownikAdresWrapper) {
        Adres adres = pracownikAdresWrapper.getAdres();
        System.out.println("adress from wrapper: " + adres.getNrAdresu());
        System.out.println("miejscowosc from wrapper: " + adres.getMiejscowosc());
        System.out.println("ulica from wrapper: " + adres.getUlica());
        System.out.println("nrDomu from wrapper: " + adres.getNrDomu());
        System.out.println("nrLokalu from wrapper: " + adres.getNrLokalu());
        System.out.println("poczta from wrapper: " + adres.getPoczta().getNrPoczty());
        Pracownik pracownik = pracownikAdresWrapper.getPracownik();

        if (adres != null) {
            if (adres.getNrAdresu() != null) {
                // Ensure that the Adres is properly attached to the Hibernate session
                Adres existingAdres = adressService.findById(adres.getNrAdresu());
                for (int i = 0; i < 10; i++) System.out.println("ADRESS");
                System.out.println("Existing Adres: " + existingAdres.getNrAdresu());
                System.out.println("Existing Miejscowosc: " + existingAdres.getMiejscowosc());
                System.out.println("Existing Ulica: " + existingAdres.getUlica());
                System.out.println("Existing NrDomu: " + existingAdres.getNrDomu());
                System.out.println("Existing NrLokalu: " + existingAdres.getNrLokalu());
                System.out.println("Existing Poczta: " + existingAdres.getPoczta().getNrPoczty());

                if (existingAdres != null) {
                    System.out.println("weszlo tu 1");

                    // Update fields of the existing Adres if necessary
                    existingAdres.setMiejscowosc(adres.getMiejscowosc());
                    existingAdres.setUlica(adres.getUlica());
                    existingAdres.setNrDomu(adres.getNrDomu());
                    existingAdres.setNrLokalu(adres.getNrLokalu());
                    existingAdres.setPoczta(adres.getPoczta());

                    // Link the existing Adres to the Pracownik
                    adressService.save(existingAdres);
                    pracownik.setAdres(existingAdres);
                } else {
                    System.out.println("weszlo tu 2");
                    // If the Adres doesn't exist, create a new one
                    adres.setNrAdresu(adressService.getNextId());
                    adressService.save(adres);
                    pracownik.setAdres(adres);
                }
            } else {
                System.out.println("weszlo tu 3");
                // If No Adres ID is available, create a new one
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
        final Pracownik pracownik = pracownicyService.findById(id); // Fetch the object from the database
        final Long adresId = pracownik.getAdres().getNrAdresu();
        pracownicyService.remove(id); // Remove the object from the database
        adressService.remove(adresId); // Remove the related Adres from the database
        return "redirect:/admin"; // Redirect to the list view after removing
    }
}
