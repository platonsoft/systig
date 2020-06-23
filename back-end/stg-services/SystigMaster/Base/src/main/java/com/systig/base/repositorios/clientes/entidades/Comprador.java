package com.systig.base.repositorios.clientes.entidades;

import com.systig.base.repositorios.nominas.entidades.Empresa;
import com.systig.base.repositorios.nominas.entidades.Persona;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "STG_CLI_COMPRADORES")
public class Comprador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprador;
    private Boolean isCliente; // cliente o prospecto

    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne()
    private Empresa idEmpresa;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne()
    private Persona idPersona;

    @JoinColumn(name = "id_etapa", referencedColumnName = "idEtapa")
    @OneToOne()
    private Etapa etapa;

    @JoinColumn(name = "id_campana", referencedColumnName = "idCampana")
    @ManyToOne()
    private CampanaPublicidad campanaPublicidad;

    private Long status;

    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion;
}
