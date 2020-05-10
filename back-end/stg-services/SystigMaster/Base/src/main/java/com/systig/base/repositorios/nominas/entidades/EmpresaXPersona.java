package com.systig.base.repositorios.nominas.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_NOM_EMPRESA_PERSONA")
public class EmpresaXPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa_persona")
    private Long idEmpresaXPersona;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne()
    private Persona idPersona;

    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne()
    private Empresa idEmpresa;

    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo", nullable = false)
    @ManyToOne(optional = false)
    private Cargo idCargo;
}
