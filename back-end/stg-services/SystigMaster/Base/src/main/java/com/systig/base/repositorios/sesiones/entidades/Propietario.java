package com.systig.base.repositorios.sesiones.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "STG_PROPIETARIOS")
public class Propietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propietario")
    private Long idPropietario;
    private Integer tipo; // Persona o Empresa
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

    @OneToMany( mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Usuario> usuarios = new ArrayList<>();

    @OneToMany( mappedBy = "idPropietario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductoSystigXPropietario> productosSystig = new ArrayList<>();
}
