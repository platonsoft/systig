package com.systig.systigmaster.inventario.modelos;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private Propietario propietario;
}

