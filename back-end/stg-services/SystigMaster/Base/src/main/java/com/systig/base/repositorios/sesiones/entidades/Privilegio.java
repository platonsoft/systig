package com.systig.base.repositorios.sesiones.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_PRIVILEGIOS")
public class Privilegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrivilegio;
    private String codigoPermiso;

    @JoinColumn(name = "id_producto_systig", referencedColumnName = "id_producto_systig", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference(value = "producto-systig")
    private ProductoSystig productoSystig;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference(value = "privilegios")
    private Usuario idUsuario;
}
