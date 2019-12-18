package com.systig.systigmaster.clientes.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_CLI_CAMPANAS_PUBLICIDAD_DETALLES")
public class CampanaPublicidadDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCampanaDetalle;
    private String descripcion;
    private String tipoPublicidad;
    private String periodo;
    @JoinColumn(name = "id_campana", referencedColumnName = "idCampana", nullable = false)
    @ManyToOne()
    private CampanaPublicidad campanaPublicidad;
}
