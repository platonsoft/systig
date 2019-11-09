package com.systig.systigmaster.sesiones.repositorios.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "STG_USUARIOS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private Boolean enabled;

    @JoinColumn(name = "id_rol", referencedColumnName = "idRol", nullable = false)
    @ManyToOne(optional = false)
    private Rol rol;

    private String ipRemota;
    private Long fecha;

    @JoinColumn(name = "id_propietario", referencedColumnName = "idPropietario", nullable = false)
    @ManyToOne(optional = false)
    private Propietario propietario;

    @OneToMany( mappedBy = "idUsuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "privilegios")
    private List<Privilegio> privilegios;

}

