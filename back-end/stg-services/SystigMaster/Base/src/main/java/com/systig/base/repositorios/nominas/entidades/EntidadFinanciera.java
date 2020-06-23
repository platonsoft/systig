package com.systig.base.repositorios.nominas.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", nullable = false)
    @ManyToOne(optional = false)
    private Pais pais;
    private Boolean isActivo;
}
