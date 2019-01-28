/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systig.api.models;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Administracion
 */
@Entity
@Table(name = "servicios_has_empleados")
public class ServiciosHasEmpleados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "duracion")
    private Double duracion;
    @Column(name = "disponible")
    private Boolean disponible;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "empleados_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empleados empleadosId;
    @JoinColumn(name = "servicios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Servicios serviciosId;

    public ServiciosHasEmpleados() {
    }

    public ServiciosHasEmpleados(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Empleados getEmpleadosId() {
        return empleadosId;
    }

    public void setEmpleadosId(Empleados empleadosId) {
        this.empleadosId = empleadosId;
    }

    public Servicios getServiciosId() {
        return serviciosId;
    }

    public void setServiciosId(Servicios serviciosId) {
        this.serviciosId = serviciosId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiciosHasEmpleados)) {
            return false;
        }
        ServiciosHasEmpleados other = (ServiciosHasEmpleados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ServiciosHasEmpleados{" +
                "id=" + id +
                ", duracion=" + duracion +
                ", disponible=" + disponible +
                ", fechaRegistro=" + fechaRegistro +
                ", empleadosId=" + empleadosId +
                ", serviciosId=" + serviciosId +
                '}';
    }
}
