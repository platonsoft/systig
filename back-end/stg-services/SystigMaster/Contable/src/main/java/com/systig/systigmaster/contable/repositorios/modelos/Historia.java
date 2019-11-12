package com.systig.systigmaster.contable.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_CONT_HISTORIAL")
public class Historia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idHistoria;
    private String elemento;
    private String descripcion;
    private String operacion;
    private Long fecha;
}
