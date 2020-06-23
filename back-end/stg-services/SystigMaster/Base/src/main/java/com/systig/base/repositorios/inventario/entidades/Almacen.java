package com.systig.base.repositorios.inventario.entidades;

import com.systig.base.repositorios.nominas.entidades.Empresa;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_INV_ALMACENES")
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlmacen;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String ubicacion;

    @JoinColumn(name = "id_propietario", referencedColumnName = "id_empresa", nullable = false)
    @ManyToOne()
    private Empresa idPropietario;
}
