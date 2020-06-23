package com.systig.base.repositorios.proveedores.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.systig.base.repositorios.nominas.entidades.Empresa;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "STG_PRV_PROVEEDORES")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @JoinColumn(name = "empresa", referencedColumnName = "id_empresa")
    @ManyToOne( fetch = FetchType.LAZY)
    private Empresa empresa;

    @JoinColumn(name = "cliente", referencedColumnName = "id_empresa")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empresa cliente;

    private String observaciones;
    private BigDecimal precioEmpaque; // la unidad sera ($/m2)
    private BigDecimal precioPeso; // la unidad sera ($/Kg)
}
