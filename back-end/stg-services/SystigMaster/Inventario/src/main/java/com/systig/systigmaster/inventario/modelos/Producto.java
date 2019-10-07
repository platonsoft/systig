package com.systig.systigmaster.inventario.modelos;

import java.math.BigDecimal;
import java.util.List;

public class Producto {
    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String unidad;
    private BigDecimal impuesto;
    private BigDecimal descuento;
    private BigDecimal cantidadMinima;
    private BigDecimal cantidadExistencia;
    private BigDecimal monto;
    private Categoria categoria;
    private Almacen almacen;
    private Proveedor proveedor;
    private Boolean disponible;
    private List<Historial> historial;
}
