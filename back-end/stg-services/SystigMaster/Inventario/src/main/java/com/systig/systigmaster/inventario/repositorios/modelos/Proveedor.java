package com.systig.systigmaster.inventario.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_INV_PROVEEDORES")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProveedor;
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
    private Long idPropietario;
}
