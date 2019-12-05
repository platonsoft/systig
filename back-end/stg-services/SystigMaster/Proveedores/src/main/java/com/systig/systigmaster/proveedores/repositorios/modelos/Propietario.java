package com.systig.systigmaster.proveedores.repositorios.modelos;

import lombok.Data;

@Data
public class Propietario {
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
    private Configuracion configuracion;
    private String provincia;
    private String pais;
    private Object usuarios;
}