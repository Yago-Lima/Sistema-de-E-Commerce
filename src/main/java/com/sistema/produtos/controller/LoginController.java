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
//    @PostMapping("/add")
//    public ModelAndView addUsuario(){
//        // Criando roles
//        Role admin = new Role("ROLE_ADMIN");
//        Role user = new Role("ROLE_USER");
//        roleRepository.save(admin);
//        roleRepository.save(user);
//
//        // Criando e salvando usuários
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        Usuario user1 = new Usuario("admin", encoder.encode("admin"), List.of(admin));
//        Usuario user2 = new Usuario("user", encoder.encode("123"), List.of(user));
//
//        usuarioRepository.save(user1);
//        usuarioRepository.save(user2);
//
//        System.out.println("*****************************\nUsuarios Criados*****************************\n");
//
//        // Retornando para a página de login após a criação
//        return new ModelAndView("login/login").addObject("message", "Usuários criados com sucesso!");
//    }
}
