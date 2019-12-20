package com.systig.base.repositorios.contable.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_ALERTAS")
public class AlertaFinanzas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlerta;
    private Long riezgo;
    private Long nivel;
    private String descripcionCorta;
    private String descripcionLarga;
    private String objetoAfectado;
    private String recomendacion;
    private String solucion;
    private Long leida;
    private Long aplicada;
    private Long fechaEvento;
    private Long idUsuario;
    private Long idPropietario;

}
