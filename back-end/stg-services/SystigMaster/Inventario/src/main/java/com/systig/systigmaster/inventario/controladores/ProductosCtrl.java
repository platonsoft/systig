package com.systig.systigmaster.inventario.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductosCtrl {

    @RequestMapping("/api/hola")
    public String saludo() {
        return "Hola, jesus";
    }

    @RequestMapping("/api/login")
    public String login() {
        return "Hola, jesus";
    }
}
