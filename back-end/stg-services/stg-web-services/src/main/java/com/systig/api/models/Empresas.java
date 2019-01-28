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
@Table(name = "empresas")
public class Empresas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "rif")
    private String rif;
    @Basic(optional = false)
    @Lob
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Lob
    @Column(name = "email")
    private String email;
    @Column(name = "telfijo")
    private String telfijo;
    @Column(name = "telmovil")
    private String telmovil;
    @Lob
    @Column(name = "website")
    private String website;
    @Lob
    @Column(name = "pais")
    private String pais;
    @Lob
    @Column(name = "provincia")
    private String provincia;
    @Lob
    @Column(name = "ciudad")
    private String ciudad;
    @Lob
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "moneda")
    private String moneda;
    @Column(name = "puntos")
    private Integer puntos;
    @Column(name = "fallas")
    private Integer fallas;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresasId", fetch = FetchType.LAZY)
    private List<Empleados> empleadosCollection;
    @OneToMany(mappedBy = "empresasId", fetch = FetchType.LAZY)
    private List<Clientes> clientesCollection;

    public Empresas() {
    }

    public Empresas(Integer id) {
        this.id = id;
    }

    public Empresas(Integer id, String rif, String nombre, String email) {
        this.id = id;
        this.rif = rif;
        this.nombre = nombre;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelfijo() {
        return telfijo;
    }

    public void setTelfijo(String telfijo) {
        this.telfijo = telfijo;
    }

    public String getTelmovil() {
        return telmovil;
    }

    public void setTelmovil(String telmovil) {
        this.telmovil = telmovil;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Integer getFallas() {
        return fallas;
    }

    public void setFallas(Integer fallas) {
        this.fallas = fallas;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Empleados> getEmpleadosCollection() {
        return empleadosCollection;
    }

    public void setEmpleadosCollection(List<Empleados> empleadosCollection) {
        this.empleadosCollection = empleadosCollection;
    }

    public List<Clientes> getClientesCollection() {
        return clientesCollection;
    }

    public void setClientesCollection(List<Clientes> clientesCollection) {
        this.clientesCollection = clientesCollection;
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
        if (!(object instanceof Empresas)) {
            return false;
        }
        Empresas other = (Empresas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empresas{" +
                "id=" + id +
                ", rif='" + rif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telfijo='" + telfijo + '\'' +
                ", telmovil='" + telmovil + '\'' +
                ", website='" + website + '\'' +
                ", pais='" + pais + '\'' +
                ", provincia='" + provincia + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", moneda='" + moneda + '\'' +
                ", puntos=" + puntos +
                ", fallas=" + fallas +
                ", fechaRegistro=" + fechaRegistro +
                ", empleadosCollection=" + empleadosCollection +
                ", clientesCollection=" + clientesCollection +
                '}';
    }
}
