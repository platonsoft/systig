package com.systig.systigmaster.sesiones.utilidades;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;

public class GenPassword {
    public static void main(String[] args) {
        /*String password = "cliente";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);



        password = "admin";
        hashedPassword = passwordEncoder.encode(password);

        System.out.println(hashedPassword);*/

        LocalDateTime actualidad = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(actualidad, ZoneId.systemDefault());
        System.out.println(zdt.toInstant().toEpochMilli());
    }

}
