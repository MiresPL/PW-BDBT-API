package com.mires.bdbt.parkrozrywki.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mires.bdbt.parkrozrywki.entities.Klient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/")
@SessionAttributes("klient")
public class DefaultController {
    @GetMapping("")
    public String home(final Model model, HttpSession session, HttpServletRequest request) {
        final Object klientObject = model.getAttribute("klient");
        System.out.println(klientObject);
        System.out.println(klientObject == null);
        if (klientObject != null) {
            Klient klient = new ObjectMapper().convertValue(klientObject, Klient.class);
            model.addAttribute("klient", klient);
            session.setAttribute("klient", klient);
        }
        model.addAttribute("request", request);
        return "home/Home";
    }
}
