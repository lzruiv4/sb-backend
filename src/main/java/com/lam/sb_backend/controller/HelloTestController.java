package com.lam.sb_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloTestController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }
}
