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
@Table(name = "clientes")
public class Clientes{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "rif")
    private String rif;
    @Lob
    @Column(name = "nombres")
    private String nombres;
    @Lob
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Lob
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Lob
    @Column(name = "telMovil")
    private String telMovil;
    @Column(name = "puntos")
    private Integer puntos;
    @Column(name = "fallas")
    private Integer fallas;
    @Basic(optional = false)
    @Column(name = "moneda")
    private String moneda;
    @Basic(optional = false)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Column(name = "zonaPostal")
    private Integer zonaPostal;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientesId", fetch = FetchType.LAZY)
    private List<Contratos> contratosCollection;
    @JoinColumn(name = "empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Empresas empresasId;

    public Clientes() {
    }

    public Clientes(Integer id) {
        this.id = id;
    }

    public Clientes(Integer id, String rif, String email, String telMovil, String moneda, String nacionalidad) {
        this.id = id;
        this.rif = rif;
        this.email = email;
        this.telMovil = telMovil;
        this.moneda = moneda;
        this.nacionalidad = nacionalidad;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelMovil() {
        return telMovil;
    }

    public void setTelMovil(String telMovil) {
        this.telMovil = telMovil;
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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Integer getZonaPostal() {
        return zonaPostal;
    }

    public void setZonaPostal(Integer zonaPostal) {
        this.zonaPostal = zonaPostal;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Contratos> getContratosCollection() {
        return contratosCollection;
    }

    public void setContratosCollection(List<Contratos> contratosCollection) {
        this.contratosCollection = contratosCollection;
    }

    public Empresas getEmpresasId() {
        return empresasId;
    }

    public void setEmpresasId(Empresas empresasId) {
        this.empresasId = empresasId;
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
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "id=" + id +
                ", rif='" + rif + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", sexo='" + sexo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", email='" + email + '\'' +
                ", telMovil='" + telMovil + '\'' +
                ", puntos=" + puntos +
                ", fallas=" + fallas +
                ", moneda='" + moneda + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", zonaPostal=" + zonaPostal +
                ", fechaRegistro=" + fechaRegistro +
                ", contratosCollection=" + contratosCollection +
                ", empresasId=" + empresasId +
                '}';
    }
}
