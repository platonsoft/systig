package com.systig.base.repositorios.nominas.entidades;

import com.fasterxml.jackson.annotation.*;
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

    @JoinColumn(name = "id_tipo_doc_identif", referencedColumnName = "id_tipo_doc_identif", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoDocumentoIdentif tipoIdentificacion;
    @Column(nullable = false)
    private String nroIdentificacion;
    @Column(nullable = false)
    private String razonSocial;
    @Column(nullable = false)
    private String telefonoLocal;
    @Column(nullable = false)
    private String telefonoMovil;
    @Column(nullable = false)
    private String email;
    private String sitioWeb;
    @Column(nullable = false)
    private String direccionFiscal;
    @Column(nullable = false)
    private String codigoPostal;
    @Column(nullable = false)
    private String provincia;

    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pais id_pais;

    @JoinColumn(name = "id_configuracion", referencedColumnName = "id_configuracion")
    @ManyToOne()
    @JsonIgnore()
    private Configuracion configuracion;

    @OneToMany( mappedBy = "idEmpresa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore()
    private List<ProductoSystigXPropietario> productosSystig = new ArrayList<>();
}
