package com.systig.base.repositorios.contable.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_CONT_METODO_PAGO")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMetodoPago;
    private String descripcion;
    private Boolean isBanco;
}
