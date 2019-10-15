package com.systig.systigmaster.repositorios.modelos.compradores;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_INV_CAMPANAS_PUBLICIDAD_DETALLES")
public class CampanaPublicidadDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCampanaDetalle;
    private String descripcion;
    private String tipoPublicidad;
    private String periodo;
    @JoinColumn(name = "id_campana", referencedColumnName = "idCampana", nullable = false)
    @ManyToOne()
    private CampanaPublicidad campanaPublicidad;
}
