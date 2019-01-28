/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systig.api.models;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Administracion
 */
@Entity
@Table(name = "clientes_has_servicios")
public class ClientesHasServicios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "duracion")
    private Double duracion;
    @Column(name = "precio")
    private BigDecimal precio;
    @Column(name = "progreso")
    private Double progreso;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "contratos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Contratos contratosId;
    @JoinColumn(name = "servicios_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Servicios serviciosId;

    public ClientesHasServicios() {
    }

    public ClientesHasServicios(Integer id) {
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Double getProgreso() {
        return progreso;
    }

    public void setProgreso(Double progreso) {
        this.progreso = progreso;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Contratos getContratosId() {
        return contratosId;
    }

    public void setContratosId(Contratos contratosId) {
        this.contratosId = contratosId;
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
        if (!(object instanceof ClientesHasServicios)) {
            return false;
        }
        ClientesHasServicios other = (ClientesHasServicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClientesHasServicios{" +
                "id=" + id +
                ", duracion=" + duracion +
                ", precio=" + precio +
                ", progreso=" + progreso +
                ", fechaRegistro=" + fechaRegistro +
                ", contratosId=" + contratosId +
                ", serviciosId=" + serviciosId +
                '}';
    }
}
