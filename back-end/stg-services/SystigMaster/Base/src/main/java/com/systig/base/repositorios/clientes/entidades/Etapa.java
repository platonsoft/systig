package com.systig.base.repositorios.clientes.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_CLI_ETAPAS")
public class Etapa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtapa;
    private String nombre;
    private String descripcion;
}
