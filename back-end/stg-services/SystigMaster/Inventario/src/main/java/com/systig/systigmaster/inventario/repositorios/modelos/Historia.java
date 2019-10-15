package com.systig.systigmaster.inventario.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_INV_HISTORIAL")
public class Historia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idHistoria;
    private String elemento;
    private String descripcion;
    private String operacion;
    private Long fecha;
}
