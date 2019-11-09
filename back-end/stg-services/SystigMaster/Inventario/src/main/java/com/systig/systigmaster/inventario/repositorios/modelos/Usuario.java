package com.systig.systigmaster.inventario.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
public class Usuario {
    private Long idUsuario;
    private String username;
    private String password;
    private Boolean enabled;
    private Object rol;
    private String ipRemota;
    private Propietario propietario;
    private Object privilegios;
    private Long fecha;
}

