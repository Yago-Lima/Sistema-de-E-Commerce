package com.sistema.produtos.controller;

import com.sistema.produtos.model.Role;
import com.sistema.produtos.model.Usuario;
import com.sistema.produtos.repository.RoleRepository;
import com.sistema.produtos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public ModelAndView form() {
        return new ModelAndView("login/login");
    }

}
