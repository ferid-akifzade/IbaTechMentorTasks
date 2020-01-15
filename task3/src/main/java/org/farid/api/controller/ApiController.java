package org.farid.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping
    public String getHome() {
        return "Ok: Home ";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "Ok: Login ";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "Ok: Register";
    }

}
