package com.elnura.task.megacom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/auth")
    public String testEndpoint() {
        return "Basic Auth works!";
    }
}
