package com.systig.systigmaster.clientes.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_INV_ETAPAS")
public class Etapa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEtapa;
    private String nombre;
    private String descripcion;
}
