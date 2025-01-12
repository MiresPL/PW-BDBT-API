package com.mires.bdbt.parkrozrywki.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mires.bdbt.parkrozrywki.entities.Klient;
import com.mires.bdbt.parkrozrywki.services.KlientService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/klient/login")
public class LoginController {
    private final KlientService klientService;

    public LoginController(KlientService klientService) {
        this.klientService = klientService;
    }



    @PostMapping
    public String login(@RequestParam String login, @RequestParam String password) {
        final Klient klient = klientService.login(login, password);
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", klient != null);
        try {
            jsonObject.put("klient", (klient != null ? new ObjectMapper().writeValueAsString(klient) : ""));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonObject.toString();
    }
}
