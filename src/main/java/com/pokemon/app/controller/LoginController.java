package com.pokemon.app.controller;

import com.pokemon.app.request.UserRequest;
import com.pokemon.app.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {


    private LoginService loginService;


    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLoginPage(){

        return "login";
    }

    @PostMapping("/login")
    public String loginUser(String email,String password, Model model){
        model.addAttribute("message","Udało się pomyślnie zalogować!");//TODO refactor
        try {
            UserRequest userRequest = new UserRequest(email, password);
            loginService.loginUser(userRequest);
        } catch(Exception e){
            model.addAttribute("message",e.getMessage());
        }
        return "login-result";
    }
}
