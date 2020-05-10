package com.systig.base.repositorios.nominas.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.base.repositorios.sesiones.entidades.Configuracion;
import com.systig.base.repositorios.sesiones.entidades.ProductoSystigXPropietario;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "STG_NOM_EMPRESAS")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Long idEmpresa;
    private Long rol; // Comprador, usuario
    private String tipoIdentificacion;
    private String nroIdentificacion;
    private String razonSocial;
    private String telefonoLocal;
    private String telefonoMovil;
    private String email;
    private String sitioWeb;
    private String direccionFiscal;
    private String codigoPostal;
    private String provincia;
    private String pais;

    @JoinColumn(name = "id_configuracion", referencedColumnName = "id_configuracion")
    @ManyToOne()
    private Configuracion configuracion;

    @OneToMany( mappedBy = "idEmpresa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductoSystigXPropietario> productosSystig = new ArrayList<>();
}
