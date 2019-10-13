package com.systig.systigmaster.inventario.modelos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Producto {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String unidad;
    private BigDecimal impuesto;
    private BigDecimal descuento;
    private BigDecimal cantidadMinima;
    private BigDecimal cantidadOptima;
    private BigDecimal cantidadExistencia;
    private BigDecimal monto;
    private Categoria categoria;
    private Almacen almacen;
    private Proveedor proveedor;
    private Propietario propietario;
    private Boolean disponible;
}
