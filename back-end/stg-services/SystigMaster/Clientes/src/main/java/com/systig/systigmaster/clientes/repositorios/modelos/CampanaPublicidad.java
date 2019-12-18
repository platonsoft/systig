package com.systig.systigmaster.clientes.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_CLI_CAMPANAS_PUBLICIDAD")
public class CampanaPublicidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCampana;
    private String titulo;
    private String descripcion;
    private Long idPropietario;
    private String alcance;
    private String ciudad;
    private String provincia;
    private String pais;
    private String username;
    private Long validaDesde;
    private Long validaHasta;
}
