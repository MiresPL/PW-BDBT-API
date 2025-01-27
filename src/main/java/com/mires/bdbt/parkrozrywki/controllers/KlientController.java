package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.*;
import com.mires.bdbt.parkrozrywki.services.BiletyKlienciService;
import com.mires.bdbt.parkrozrywki.services.BiletyService;
import com.mires.bdbt.parkrozrywki.services.KlientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.nio.charset.Charset;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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
        } else return "redirect:/klient/login?error";


    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, final Model model, HttpServletRequest request) {
        //throw HttpServerErrorException.create(HttpStatus.INTERNAL_SERVER_ERROR, "Niepoprawne dane logowania", HttpHeaders.EMPTY, "Niepoprawne dane logowania".getBytes(Charset.defaultCharset()), Charset.defaultCharset());
        model.addAttribute("loginCredentials", new LoginCredentials());
        model.addAttribute("request", request);
        if (error != null) {
            model.addAttribute("error", "Niepoprawne dane logowania.");
        }
        return "login/Login";
    }

    @GetMapping("/logout")
    public String logout(final Model model, final HttpSession session) {
        model.addAttribute("klient", null);
        session.setAttribute("klient", null);
        return "redirect:/";
    }

    @GetMapping("/profil")
    public String profil(final Model model, HttpSession session, HttpServletRequest request) {
        final Klient klient = (Klient) session.getAttribute("klient");
        final List<KlientBilet> tickets = new ArrayList<>();

        for (Bilet bilet : biletyService.getTicketsByKlient(klient.getNrKlienta())) {
            final List<BiletyKlienci> list = biletyKlienciService.findByNrKlientaAndNrBiletu(klient.getNrKlienta(), bilet.getNrBiletu());
            for (BiletyKlienci biletyKlienci : list) {
                tickets.add(new KlientBilet(bilet, biletyKlienci));
            }
        }

        model.addAttribute("klient", klient);
        model.addAttribute("tickets", tickets);
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
        return "redirect:/klient/profil";
    }
}