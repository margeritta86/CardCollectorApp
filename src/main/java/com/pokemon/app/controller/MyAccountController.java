package com.pokemon.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MyAccountController {

    @GetMapping("/my-account")
    public String getMyAccountPage(){

        return "my-account";
    }
}
