package com.systig.base.repositorios.nominas.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_NOM_EMPLEADOS")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;
    private String tipoIdentificacion;
    private String nroIdentificacion;
    private String nombres;
    private String apellidos;
    private String telefonoMovil;
    private String email;
    private String direccion;

}
