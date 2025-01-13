package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.BiletyKlienci;
import com.mires.bdbt.parkrozrywki.entities.Klient;
import com.mires.bdbt.parkrozrywki.entities.LoginCredentials;
import com.mires.bdbt.parkrozrywki.services.BiletyKlienciService;
import com.mires.bdbt.parkrozrywki.services.KlientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;


@Controller
@RequestMapping("/klient")
@SessionAttributes("klient")
public class KlientController {
    private final KlientService klientService;
    private final BiletyKlienciService biletyKlienciService;

    public KlientController(KlientService klientService, BiletyKlienciService biletyKlienciService) {
        this.klientService = klientService;
        this.biletyKlienciService = biletyKlienciService;
    }

    @PostMapping(path = "/loginRequest")
    public String loginRequest(@ModelAttribute LoginCredentials loginCredentials, final HttpSession session, SessionStatus sessionStatus) {
        final Klient klient = klientService.login(loginCredentials.getLogin(),  loginCredentials.getPassword());

        if (klient != null) {
            session.setAttribute("klient", klient);

            return "redirect:/";
        } else {

            return "redirect:/klient/login";
        }

    }

    @GetMapping("/login")
    public String login(final Model model, HttpServletRequest request) {
        model.addAttribute("loginCredentials", new LoginCredentials());
        model.addAttribute("request", request);
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