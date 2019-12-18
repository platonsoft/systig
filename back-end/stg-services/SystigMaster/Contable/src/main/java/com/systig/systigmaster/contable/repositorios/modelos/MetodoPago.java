package com.systig.systigmaster.contable.repositorios.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

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
