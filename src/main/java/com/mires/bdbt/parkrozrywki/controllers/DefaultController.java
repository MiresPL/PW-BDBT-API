package com.mires.bdbt.parkrozrywki.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mires.bdbt.parkrozrywki.entities.Klient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DefaultController {
    @GetMapping("")
    public String home(final Model model) {
        final Object klientObject = model.getAttribute("klient");
        System.out.println(klientObject);
        System.out.println(klientObject == null);
        if (klientObject != null) {
            model.addAttribute("klient", new ObjectMapper().convertValue(klientObject, Klient.class));
        }
        return "home/Home";
    }
}
