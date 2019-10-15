package com.systig.systigmaster.inventario.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_INV_PROPIETARIOS")
public class Propietario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPropietario;
    private Integer tipo;
    private String tipoIdentificacion;
    private String nroIdentificacion;
    private String nombres;
    private String apellidos;
    private String razonSocial;
    private String telefonoLocal;
    private String telefonoMovil;
    private String email;
    private String direccionFiscal;
    private String codigoPostal;
    private String provincia;
    private String pais;

}
