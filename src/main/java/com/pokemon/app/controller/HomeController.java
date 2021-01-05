package com.pokemon.app.controller;

import com.pokemon.app.service.common.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomepage() {

        return "index";
    }
}
