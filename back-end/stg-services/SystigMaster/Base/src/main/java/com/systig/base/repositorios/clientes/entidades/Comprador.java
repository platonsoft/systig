package com.systig.base.repositorios.clientes.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_CLI_COMPRADORES")
public class Comprador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprador;
    private Long tipoCliente;
    private Long tipoIdentificacion;
    private String nroIdentificacion;
    private Long idEmpresa;
    @JoinColumn(name = "id_etapa", referencedColumnName = "idEtapa")
    @OneToOne()
    private Etapa etapa;
    @JoinColumn(name = "id_campana", referencedColumnName = "idCampana")
    @ManyToOne()
    private CampanaPublicidad campanaPublicidad;
}
