package com.lam.sb_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String login(){
//        this.iUserService.loadUserByUsername()
        return "login";
    }
}
