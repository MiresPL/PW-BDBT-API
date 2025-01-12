package com.mires.bdbt.parkrozrywki.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mires.bdbt.parkrozrywki.entities.Klient;
import com.mires.bdbt.parkrozrywki.services.KlientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/klient/login")
public class LoginController {
    private final KlientService klientService;
    private final Gson compactGson;

    public LoginController(KlientService klientService) {
        this.klientService = klientService;
        this.compactGson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .setVersion(1.0)
                .disableHtmlEscaping()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create();
    }



    @PostMapping
    public String login(@RequestParam String login, @RequestParam String password) {
        final Klient klient = klientService.login(login, password);
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", klient != null);
        jsonObject.addProperty("klient", (klient != null ? compactGson.toJson(klient) : ""));
        return jsonObject.toString();
    }
}
