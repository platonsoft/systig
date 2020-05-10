package com.systig.base.repositorios.nominas.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_NOM_ENTIDADES_FINANCIERAS")
public class EntidadFinanciera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidad_financiera")
    private Long idEntidadFinanciera;
    private String codigo;
    private String logotipo;
    private String nombreBanco;
    private Long idPais;
    private Boolean isActivo;
}
