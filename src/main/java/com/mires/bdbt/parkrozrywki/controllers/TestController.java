package com.mires.bdbt.parkrozrywki.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(value = "*", origins = "*")
public class TestController {
    @GetMapping("/test")
    public String getTest() {
        return "Test";
    }
}
