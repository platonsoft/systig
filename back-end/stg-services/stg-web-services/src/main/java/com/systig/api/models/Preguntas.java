/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systig.api.models;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Administracion
 */
@Entity
@Table(name = "preguntas")
public class Preguntas{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "pregunta")
    private String pregunta;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Column(name = "icono")
    private String icono;
    @Column(name = "aprobado")
    private Boolean aprobado;
    @Column(name = "base")
    private Boolean base;
    @Column(name = "multisel")
    private Boolean multisel;
    @OneToMany(mappedBy = "preguntasId", fetch = FetchType.EAGER)
    private List<Servicios> serviciosCollection;

    public Preguntas() {
    }

    public Preguntas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Boolean getBase() {
        return base;
    }

    public void setBase(Boolean base) {
        this.base = base;
    }

    public Boolean getMultisel() {
        return multisel;
    }

    public void setMultisel(Boolean multisel) {
        this.multisel = multisel;
    }

    public List<Servicios> getServiciosCollection() {
        return serviciosCollection;
    }

    public void setServiciosCollection(List<Servicios> serviciosCollection) {
        this.serviciosCollection = serviciosCollection;
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
        if (!(object instanceof Preguntas)) {
            return false;
        }
        Preguntas other = (Preguntas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Preguntas{" +
                "id=" + id +
                ", pregunta='" + pregunta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", icono='" + icono + '\'' +
                ", aprobado=" + aprobado +
                ", base=" + base +
                ", multisel=" + multisel +
                ", serviciosCollection=" + serviciosCollection +
                '}';
    }
}
