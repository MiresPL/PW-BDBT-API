package com.mires.bdbt.parkrozrywki.controllers;

import com.mires.bdbt.parkrozrywki.entities.Atrakcja;
import com.mires.bdbt.parkrozrywki.services.AtrakcjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/atrakcje")
public class AtrakcjaController {
    private final AtrakcjaService atrakcjaService;

    @Autowired
    public AtrakcjaController(AtrakcjaService atrakcjaService) {
        this.atrakcjaService = atrakcjaService;
    }

    @GetMapping
    public String listAtrakcje(Model model) {
        List<Atrakcja> atrakcjeList = atrakcjaService.findAll();
        for (Atrakcja atrakcja : atrakcjeList) {
            System.out.println("Atrakcja: " + atrakcja.getNazwaAtrakcji() + ", Zdjecie: " + atrakcja.getZdjecie());
        }
        model.addAttribute("atrakcjeList", atrakcjeList);
        return "attractions/attractions";
    }
}