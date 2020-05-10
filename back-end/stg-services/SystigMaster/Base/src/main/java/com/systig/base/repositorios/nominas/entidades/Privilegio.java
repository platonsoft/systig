package com.systig.base.repositorios.nominas.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.systig.base.repositorios.nominas.entidades.Cargo;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.base.repositorios.sesiones.entidades.ProductoSystig;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_NOM_PRIVILEGIOS")
public class Privilegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrivilegio;
    private String codigoPermiso;

    @JoinColumn(name = "id_producto_systig", referencedColumnName = "id_producto_systig", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference(value = "producto-systig")
    private ProductoSystig productoSystig;

    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference(value = "privilegios")
    private Cargo idCargo;
}
