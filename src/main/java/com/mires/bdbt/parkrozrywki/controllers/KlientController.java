package com.mires.bdbt.parkrozrywki.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mires.bdbt.parkrozrywki.entities.BiletyKlienci;
import com.mires.bdbt.parkrozrywki.entities.Klient;
import com.mires.bdbt.parkrozrywki.entities.LoginCredentials;
import com.mires.bdbt.parkrozrywki.services.BiletyKlienciService;
import com.mires.bdbt.parkrozrywki.services.KlientService;
import oracle.sql.DATE;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;


@Controller
@RequestMapping("/klient")
public class KlientController {
    private final KlientService klientService;
    private final BiletyKlienciService biletyKlienciService;

    public KlientController(KlientService klientService, BiletyKlienciService biletyKlienciService) {
        this.klientService = klientService;
        this.biletyKlienciService = biletyKlienciService;
    }

    @PostMapping(path = "/loginRequest")
    public String loginRequest(@ModelAttribute LoginCredentials loginCredentials, final RedirectAttributes redirectAttributes) {
        final Klient klient = klientService.login(loginCredentials.getLogin(),  loginCredentials.getPassword());

        if (klient != null) {
            redirectAttributes.addFlashAttribute("klient", klient);
            return "redirect:/";
        } else {

            return "redirect:/klient/login";
        }

    }

    @GetMapping("/login")
    public String login(final Model model) {
        model.addAttribute("loginCredentials", new LoginCredentials());
        return "login/Login";
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
        final Date dataZakupu = new Date(System.currentTimeMillis());
        biletyKlienci.setDataZakupu(dataZakupu);
        biletyKlienci.setDataWaznosci(new Date(dataZakupu.getTime() + 1000 * 60 * 60 * 24 * 10));
        biletyKlienciService.save(biletyKlienci);
        return "redirect:/";
    }
}
