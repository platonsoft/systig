package com.systig.systigmaster.proveedores.repositorios.modelos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProveedor;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String razonSocial;
    private String tipoCliente;
    private String telefonoLocal;
    private String telefonoMovil;
    private String email;
    private String direccionFiscal;
    private String codigoPostal;
    private String ciudad;
    private String provincia;
    private String pais;
    private Long idPropietario;
}
