package com.systig.base.repositorios.contable.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_CONT_FORMA_PAGO")
public class FormaPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormaPago;
    private String tipoMonto; // Al detal y al mayor
    private String forma;
    private Long nroCuotas;
}
