package com.pokemon.app.controller;

import com.pokemon.app.request.UserRequest;
import com.pokemon.app.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {


    private RegisterService registerService;


    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String getRegisterPage() {

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(String email, String password, Model model) {

        model.addAttribute("message","Udało się pomyślnie zarejestrować!");//TODO refactor

        try {
            UserRequest userRequest = new UserRequest(email, password);
            registerService.registerUser(userRequest);
        } catch(Exception e){
            model.addAttribute("message",e.getMessage());
        }
        return "register-result";

    }
}
