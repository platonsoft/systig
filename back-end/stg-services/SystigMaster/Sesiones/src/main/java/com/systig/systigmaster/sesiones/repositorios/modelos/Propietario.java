package com.systig.systigmaster.sesiones.repositorios.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "STG_PROPIETARIOS")
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

    @OneToMany( mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "usuarios")
    private List<Usuario> usuarios = new ArrayList<>();
}
