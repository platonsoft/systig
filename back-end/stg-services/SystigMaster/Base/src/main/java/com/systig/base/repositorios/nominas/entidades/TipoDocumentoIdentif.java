package com.systig.base.repositorios.nominas.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "STG_NOM_TIPO_DOC_IDENTIF")
public class TipoDocumentoIdentif implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_doc_identif")
    private Long idTipoDocIdentif;
    private String nombreDoc;
    private String abrevDoc;

    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", nullable = false)
    @ManyToOne()
    private Pais pais;
    private Boolean isEmpresa;
    private Boolean isActivo;
}
