package com.systig.systigmaster.inventario.repositorios.modelos;

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
    private Long idPropietario;
}
