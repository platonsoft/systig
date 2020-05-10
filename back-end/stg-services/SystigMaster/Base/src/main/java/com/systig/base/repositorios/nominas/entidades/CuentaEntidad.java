package com.systig.base.repositorios.nominas.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "STG_NOM_CUENTA_ENTIDAD")
public class CuentaEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta_entidad")
    private Long idCuentaEntidad;
    private String nroCuenta;
    private String tipoCuenta;
    @JoinColumn(name = "id_entidad_financiera", referencedColumnName = "id_entidad_financiera", nullable = false)
    @ManyToOne(optional = false)
    private EntidadFinanciera entidadFinanciera;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaActualizacion;

    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference(value = "cuentasEntidad")
    private Persona idPersona;
}
