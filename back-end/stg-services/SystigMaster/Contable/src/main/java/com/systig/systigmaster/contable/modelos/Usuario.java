package com.systig.systigmaster.contable.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_USUARIOS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    private String username;
    private String password;
    private Boolean enabled;
    private Integer codigoSystig;
    private String rol;
    private Integer nivelAcceso;
    private String ipRemota;
    private String sessionId;
    private Long fecha;
    @JoinColumn(name = "id_propietario", referencedColumnName = "idPropietario", nullable = false)
    @ManyToOne(optional = false)
    private Propietario propietario;

}

