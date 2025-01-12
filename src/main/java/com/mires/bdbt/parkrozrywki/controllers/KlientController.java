package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.BiletyKlienci;
import com.mires.bdbt.parkrozrywki.entities.Klient;
import com.mires.bdbt.parkrozrywki.services.BiletyKlienciService;
import com.mires.bdbt.parkrozrywki.services.KlientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
@RequestMapping("/klient")
public class KlientController {
    private final KlientService klientService;
    private final BiletyKlienciService biletyKlienciService;

    public KlientController(KlientService klientService, BiletyKlienciService biletyKlienciService) {
        this.klientService = klientService;
        this.biletyKlienciService = biletyKlienciService;
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Klient klient = klientService.findById(id);
        model.addAttribute("klient", klient);
        return "klient/add";
    }

    @PostMapping("/add")
    public String saveKlient(@ModelAttribute("klient") Klient klient) {
        klientService.save(klient);
        return "redirect:/";
    }

    @PostMapping("/buyTicket")
    public String buyTicket(@RequestParam Long idKlienta, @RequestParam Long idBiletu) {
        BiletyKlienci biletyKlienci = new BiletyKlienci();
        biletyKlienci.setNrKlienta(idKlienta);
        biletyKlienci.setNrBiletu(idBiletu);
        final LocalDate dataZakupu = LocalDate.now();
        biletyKlienci.setDataZakupu(dataZakupu);
        biletyKlienci.setDataWaznosci(dataZakupu.plusDays(10));
        biletyKlienciService.save(biletyKlienci);
        return "redirect:/";
    }
}
