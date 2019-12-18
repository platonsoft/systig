package com.systig.systigmaster.inventario.repositorios.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "STG_INV_PRODUCTOS")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String unidad;
    private BigDecimal cantidadMinima;
    private BigDecimal cantidadOptima;
    private BigDecimal cantidadExistencia;
    private BigDecimal montoCompra;
    private BigDecimal montoUnicoDetal;
    private BigDecimal montoUnicoMayor;
    private BigDecimal montoCuotasDetal;
    private BigDecimal montoCuotasMayor;
    private BigDecimal peso;
    private BigDecimal altura;
    private BigDecimal anchura;
    private BigDecimal profundidad;
    private BigDecimal factorGanancia;
    private String modelo;

    @JoinColumn(name = "id_categoria", referencedColumnName = "idCategoria", nullable = false)
    @ManyToOne()
    private Categoria categoria;

    @JoinColumn(name = "id_almacen", referencedColumnName = "idAlmacen", nullable = false)
    @ManyToOne()
    private Almacen almacen;

    private Long idProveedor;
    private Long idPropietario;
    private Boolean isExcento;

    @OneToMany( mappedBy = "idTributo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tributo> tributos;

    @OneToMany( mappedBy = "idProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ItemProducto> itemsProductos;

}
