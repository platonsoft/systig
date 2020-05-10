package com.systig.base.repositorios.nominas.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_PAISES")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Long idPais;
    private String codPais;
    private String nombre;
    private String codMoneda;
    private Boolean isDisponible;
}
