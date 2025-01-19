package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.BiletyKlienci;
import com.mires.bdbt.parkrozrywki.entities.Klient;
import com.mires.bdbt.parkrozrywki.entities.KlientBilet;
import com.mires.bdbt.parkrozrywki.entities.LoginCredentials;
import com.mires.bdbt.parkrozrywki.services.BiletyKlienciService;
import com.mires.bdbt.parkrozrywki.services.BiletyService;
import com.mires.bdbt.parkrozrywki.services.KlientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.sql.Date;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/klient")
@SessionAttributes("klient")
public class KlientController {
    private final KlientService klientService;
    private final BiletyService biletyService;
    private final BiletyKlienciService biletyKlienciService;

    public KlientController(KlientService klientService, BiletyService biletyService,  BiletyKlienciService biletyKlienciService) {
        this.klientService = klientService;
        this.biletyService = biletyService;
        this.biletyKlienciService = biletyKlienciService;
    }

    @PostMapping(path = "/loginRequest")
    public String loginRequest(@ModelAttribute LoginCredentials loginCredentials, final HttpSession session) {
        final Klient klient = klientService.login(loginCredentials.getLogin(),  loginCredentials.getPassword());

        if (klient != null) {
            session.setAttribute("klient", klient);
            return "redirect:/";
        } else return "redirect:/klient/login";


    }

    @GetMapping("/login")
    public String login(final Model model, HttpServletRequest request) {
        model.addAttribute("loginCredentials", new LoginCredentials());
        model.addAttribute("request", request);
        return "login/Login";
    }
    @GetMapping("/profil")
    public String profil(final Model model, HttpSession session, HttpServletRequest request) {
        final Klient klient = (Klient) session.getAttribute("klient");
        model.addAttribute("klient", klient);
        model.addAttribute("tickets", biletyService.getTicketsByKlient(klient.getNrKlienta()).stream().map(ticket ->
                new KlientBilet(ticket, biletyKlienciService.findByNrKlientaAndNrBiletu(klient.getNrKlienta(), ticket.getNrBiletu()))
        ).collect(Collectors.toList()));
        model.addAttribute("request", request);
        return "profile/Profile";
    }

    @PostMapping("/add")
    public String saveKlient(@ModelAttribute("klient") Klient klient, final HttpSession session) {
        session.setAttribute("klient", klient);
        klientService.save(klient);
        return "redirect:/klient/profil";
    }

    @PostMapping("/buyTicket")
    public String buyTicket(@ModelAttribute BiletyKlienci biletyKlienci, final HttpSession session) {
        final Klient klient = (Klient) session.getAttribute("klient");
        biletyKlienci.setNrKlienta(klient.getNrKlienta());
        biletyKlienci.setDataZakupu(new Date(System.currentTimeMillis()));
        biletyKlienciService.save(biletyKlienci);
        return "redirect:/bilety";
    }
}