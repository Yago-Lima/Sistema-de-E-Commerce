package com.sistema.produtos.model.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorDeSenha {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        String encodedPassword = encoder.encode("123");
        System.out.println("senha-USER:"+encodedPassword);
        encodedPassword = encoder.encode("admin");
        System.out.println("senha-ADMIN:"+encodedPassword);
    }
}
