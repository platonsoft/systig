package com.systig.base.repositorios.nominas.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "STG_NOM_PERSONAS")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;

    @JoinColumn(name = "id_tipo_doc_identif", referencedColumnName = "id_tipo_doc_identif", nullable = false)
    @ManyToOne(optional = false)
    private TipoDocumentoIdentif tipoIdentificacion;
    private String nroIdentificacion;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String telefonoMovil;
    private String email;
    private String direccion;
    private String provincia;

    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", nullable = false)
    @ManyToOne(optional = false)
    private Pais pais;
    private String codigoPostal;
    private String foto64;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private Boolean enabled;
    private String ipRemota;
    private Long fecha;
    private Long rol; // Comprador, usuario, administrador

    @OneToMany( mappedBy = "idNotificacion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "notificaciones")
    private List<Notificacion> notificaciones;

    @OneToMany( mappedBy = "idCuentaEntidad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "cuentasEntidad")
    private List<CuentaEntidad> cuentasEntidad;
    private Long ranking;
}
