/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systig.api.models;

import java.util.List;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Administracion
 */
@Entity
@Table(name = "contratos")
public class Contratos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date desde;
    @Column(name = "hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hasta;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "clientes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Clientes clientesId;
    @OneToMany(mappedBy = "contratosId", fetch = FetchType.LAZY)
    private List<ClientesHasServicios> clientesHasServiciosCollection;

    public Contratos() {
    }

    public Contratos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Clientes getClientesId() {
        return clientesId;
    }

    public void setClientesId(Clientes clientesId) {
        this.clientesId = clientesId;
    }

    public List<ClientesHasServicios> getClientesHasServiciosCollection() {
        return clientesHasServiciosCollection;
    }

    public void setClientesHasServiciosCollection(List<ClientesHasServicios> clientesHasServiciosCollection) {
        this.clientesHasServiciosCollection = clientesHasServiciosCollection;
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
        if (!(object instanceof Contratos)) {
            return false;
        }
        Contratos other = (Contratos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contratos{" +
                "id=" + id +
                ", desde=" + desde +
                ", hasta=" + hasta +
                ", status=" + status +
                ", clientesId=" + clientesId +
                ", clientesHasServiciosCollection=" + clientesHasServiciosCollection +
                '}';
    }
}
