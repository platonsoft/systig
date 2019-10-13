package com.systig.systigmaster.inventario.modelos;

import lombok.Data;

@Data
public class Proveedor {
    private Long id;
    private Long tipoDocumento;
    private String numeroDocumento;
    private String razonSocial;
    private String telefonoLocal;
    private String telefonoMovil;
    private String email;
    private String web;
    private String direccionFiscal;
    private String codigoPostal;
    private String provincia;
    private String pais;
}
