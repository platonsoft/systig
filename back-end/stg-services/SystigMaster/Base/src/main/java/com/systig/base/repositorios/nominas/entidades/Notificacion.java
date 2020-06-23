package com.systig.base.repositorios.nominas.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.systig.base.repositorios.nominas.entidades.Persona;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "STG_NOM_NOTIFICACIONES")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Long idNotificacion;
    private String titulo;
    private String descripcion;
    private LocalTime fechaCreacion;
    private LocalTime fechaVisualizacion;
    private Boolean isVisto;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference(value = "notificaciones")
    private Persona idPersona;

}
