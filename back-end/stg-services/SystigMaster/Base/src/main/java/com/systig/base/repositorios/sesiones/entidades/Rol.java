package com.systig.base.repositorios.sesiones.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_USUARIOS_ROLES")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;
    private String username;
    private String role;
    private String descripcion;
}
