package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.LoginCredentials;
import com.mires.bdbt.parkrozrywki.entities.Pracownik;
import com.mires.bdbt.parkrozrywki.entities.Wlasciciel;
import com.mires.bdbt.parkrozrywki.services.PracownicyService;
import com.mires.bdbt.parkrozrywki.services.WlascicielService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
@SessionAttributes("admin")
public class WlascicielController {
    private final WlascicielService wlascicielService;
    private PracownicyService pracownicyService;

    public WlascicielController(WlascicielService wlascicielService, PracownicyService pracownicyService) {
        this.wlascicielService = wlascicielService;
        this.pracownicyService = pracownicyService;
    }

    @GetMapping
    public List<Pracownik> admin(final Model model) {
        model.addAttribute("pracownicy", pracownicyService.getAllPracownicy());

        return pracownicyService.getAllPracownicy();
    }

    @PostMapping(path = "/loginRequest")
    public String loginRequest(@ModelAttribute LoginCredentials loginCredentials, final HttpSession session) {
        final Wlasciciel wlasciciel = wlascicielService.login(loginCredentials.getLogin(),  loginCredentials.getPassword());

        if (wlasciciel != null) {
            session.setAttribute("admin", wlasciciel);
            return "redirect:/admin";
        } else return "redirect:/admin/login";
    }

    @GetMapping("/login")
    public String login(final Model model, HttpServletRequest request) {
        model.addAttribute("loginCredentials", new LoginCredentials());
        model.addAttribute("request", request);
        return "admin/Login";
    }
}