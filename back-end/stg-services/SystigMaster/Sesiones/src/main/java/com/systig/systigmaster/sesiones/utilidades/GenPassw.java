package com.systig.systigmaster.sesiones.utilidades;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenPassw {
    public static void main(String[] args) {
        String password = "2314441819114512";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);

        password = "admin";
        hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);
    }

}
