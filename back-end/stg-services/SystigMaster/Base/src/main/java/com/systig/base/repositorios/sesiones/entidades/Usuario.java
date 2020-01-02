package com.systig.base.repositorios.sesiones.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.systig.base.repositorios.nominas.entidades.Empleado;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "STG_USUARIOS")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private Boolean enabled;

    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @OneToOne()
    private Empleado empleado;

    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", nullable = false)
    @ManyToOne(optional = false)
    private Rol rol;

    private String ipRemota;
    private Long fecha;

    @JoinColumn(name = "id_propietario", referencedColumnName = "id_propietario", nullable = false)
    @ManyToOne(optional = false)
    private Propietario propietario;

    @OneToMany( mappedBy = "idUsuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "privilegios")
    private List<Privilegio> privilegios;

}

