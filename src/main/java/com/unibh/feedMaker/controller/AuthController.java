package com.unibh.feedMaker.controller;

import com.unibh.feedMaker.dto.LoginRequest;
import com.unibh.feedMaker.dto.LoginResponse;
import com.unibh.feedMaker.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }
}

