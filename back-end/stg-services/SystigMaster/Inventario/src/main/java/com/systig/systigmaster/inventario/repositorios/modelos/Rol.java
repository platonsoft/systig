package com.systig.systigmaster.inventario.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_USUARIOS_ROLES")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRol;
    private String username;
    private String role;
    private String descripcion;
}
