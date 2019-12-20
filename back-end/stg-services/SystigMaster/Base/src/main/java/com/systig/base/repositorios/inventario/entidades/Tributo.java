package com.systig.base.repositorios.inventario.entidades;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "STG_CONT_TRIBUTOS")
public class Tributo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTributo;
    private Long tipoTributo; // Impuesto, Descuento, Bono
    private String nombre;
    private String descripcion;
    private Boolean isObligatorio;
    private Long fechaDesde;
    private Long fechaHasta;
    private BigDecimal porcentaje;
    private BigDecimal valorUnitario;
}
