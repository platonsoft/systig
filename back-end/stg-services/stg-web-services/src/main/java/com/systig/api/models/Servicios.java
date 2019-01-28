/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systig.api.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Administracion
 */
@Entity
@Table(name = "servicios")
public class Servicios{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "titulo")
    private String titulo;
    @Lob
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Column(name = "caracteristicas")
    private String caracteristicas;
    @Lob
    @Column(name = "icono")
    private String icono;
    @Column(name = "tipo_elemento")
    private Integer tipoElemento;
    @Column(name = "valor")
    private Boolean valor;
    @Column(name = "preguntas_id")
    private Integer preguntasId;
    @OneToOne(fetch = FetchType.EAGER)
    private Preguntas nextPregunta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviciosId", fetch = FetchType.LAZY)
    private List<ServiciosHasEmpleados> serviciosHasEmpleadosCollection;
    @OneToMany(mappedBy = "serviciosId", fetch = FetchType.LAZY)
    private List<ClientesHasServicios> clientesHasServiciosCollection;

    public Servicios() {
    }

    public Preguntas getNextPregunta() {
        return nextPregunta;
    }

    public void setNextPregunta(Preguntas nextPregunta) {
        this.nextPregunta = nextPregunta;
    }

    public Servicios(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Integer getTipoElemento() {
        return tipoElemento;
    }

    public void setTipoElemento(Integer tipoElemento) {
        this.tipoElemento = tipoElemento;
    }

    public Boolean getValor() {
        return valor;
    }

    public void setValor(Boolean valor) {
        this.valor = valor;
    }

    public Integer getPreguntasId() {
        return preguntasId;
    }

    public void setPreguntasId(Integer preguntasId) {
        this.preguntasId = preguntasId;
    }

    public List<ServiciosHasEmpleados> getServiciosHasEmpleadosCollection() {
        return serviciosHasEmpleadosCollection;
    }

    public void setServiciosHasEmpleadosCollection(List<ServiciosHasEmpleados> serviciosHasEmpleadosCollection) {
        this.serviciosHasEmpleadosCollection = serviciosHasEmpleadosCollection;
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
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Servicios{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", caracteristicas='" + caracteristicas + '\'' +
                ", icono='" + icono + '\'' +
                ", tipoElemento=" + tipoElemento +
                ", valor='" + valor + '\'' +
                ", preguntasId=" + preguntasId +
                ", serviciosHasEmpleadosCollection=" + serviciosHasEmpleadosCollection +
                ", clientesHasServiciosCollection=" + clientesHasServiciosCollection +
                '}';
    }
}
