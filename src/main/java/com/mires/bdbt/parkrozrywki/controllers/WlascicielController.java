package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.LoginCredentials;
import com.mires.bdbt.parkrozrywki.entities.Pracownik;
import com.mires.bdbt.parkrozrywki.entities.Wlasciciel;
import com.mires.bdbt.parkrozrywki.services.PracownicyService;
import com.mires.bdbt.parkrozrywki.services.WlascicielService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String admin(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Wlasciciel wlasciciel = wlascicielService.findByLogin(username);
        model.addAttribute("admin", wlasciciel);
        model.addAttribute("pracownicy", pracownicyService.getAllPracownicy());

        return "admin/Admin";
    }

    @PostMapping("/loginRequest")
    public String loginRequest() {
        return "redirect:/admin"; // On success, redirect to the admin dashboard.
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Niepoprawne dane logowania.");
        }
        return "admin/Login";
    }
}