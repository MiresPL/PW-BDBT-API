package com.mires.bdbt.parkrozrywki.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mires.bdbt.parkrozrywki.entities.Klient;
import com.mires.bdbt.parkrozrywki.services.KlientService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/klient/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    private final KlientService klientService;

    public LoginController(KlientService klientService) {
        this.klientService = klientService;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping(path = "", produces = "application/json", consumes = "application/json")
    public String login(@RequestBody Map<String, String> credentials) {
        String login = credentials.get("login");
        String password = credentials.get("password");

        System.out.println(1);
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
