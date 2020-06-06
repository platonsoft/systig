package com.systig.base.repositorios.nominas.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_NOM_TIPO_DOC_IDENTIF")
public class TipoDocumentoIdentif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_doc_identif")
    private Long idTipoDocIdentif;
    private String nombreDoc;
    private String abrevDoc;

    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", nullable = false)
    @ManyToOne(optional = false)
    private Pais pais;
    private Boolean isEmpresa;
    private Boolean isActivo;
}
