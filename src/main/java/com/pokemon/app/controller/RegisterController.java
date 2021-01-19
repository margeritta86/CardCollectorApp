package com.pokemon.app.controller;

import com.pokemon.app.request.UserRequest;
import com.pokemon.app.service.common.RegisterService;
import com.pokemon.app.service.common.RegisterServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class RegisterController {


    private RegisterService registerService;


    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        UserRequest userRequest = new UserRequest();
        model.addAttribute("request", userRequest);
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("request") UserRequest userRequest, BindingResult bindingResult) {//model zawsze na końcu - inaczej nie zadziała :)

        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            registerService.registerUser(userRequest);
        } catch (RegisterServiceException e) {
            bindingResult.addError(new FieldError("request", "email", e.getMessage()));
            return "register";
        }
        return "redirect:/login";

    }
}
