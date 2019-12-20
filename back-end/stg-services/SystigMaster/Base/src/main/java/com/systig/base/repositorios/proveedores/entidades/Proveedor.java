package com.systig.base.repositorios.proveedores.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_PRV_PROVEEDORES")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String webSite;
    private String direccionFiscal;
    private String codigoPostal;
    private String ciudad;
    private String provincia;
    private String pais;
    private Boolean isRetentor;
    private Long idPropietario;

    @JoinColumn(name = "id_empresaEnvios", referencedColumnName = "idEmpresaEnvios", nullable = false)
    @ManyToOne()
    private EmpresaEnvios envios;
}
