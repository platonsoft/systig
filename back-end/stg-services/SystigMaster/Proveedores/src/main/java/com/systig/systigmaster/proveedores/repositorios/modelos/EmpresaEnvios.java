package com.systig.systigmaster.proveedores.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "STG_PRV_EMPRESAS_ENVIOS")
public class EmpresaEnvios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEmpresaEnvios;
    private String observaciones;
    private BigDecimal precioEmpaque; // la unidad sera ($/m2)
    private BigDecimal precioPeso; // la unidad sera ($/Kg)
}
