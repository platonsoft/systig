package com.systig.base.repositorios.inventario.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.systig.base.repositorios.nominas.entidades.Empresa;
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
    @Column(name = "id_producto")
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

    @JoinColumn(name = "id_empresa_proveedor", referencedColumnName = "id_empresa")
    @ManyToOne()
    private Empresa idProveedor;

    @JoinColumn(name = "id_empresa_propietario", referencedColumnName = "id_empresa")
    @ManyToOne()
    private Empresa idPropietario;

    private Boolean isExcento;

    @OneToMany( mappedBy = "idTributo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tributo> tributos;

}
