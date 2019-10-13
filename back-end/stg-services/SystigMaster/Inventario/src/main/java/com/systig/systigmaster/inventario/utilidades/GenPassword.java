package com.systig.systigmaster.inventario.utilidades;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenPassword {
    public static void main(String[] args) {
        String password = "cliente";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);
        password = "admin";
        hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);
    }

}
