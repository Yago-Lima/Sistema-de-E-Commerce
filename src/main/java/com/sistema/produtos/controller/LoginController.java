package com.sistema.produtos.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView form() {
        return new ModelAndView("login/login");
    }
}
