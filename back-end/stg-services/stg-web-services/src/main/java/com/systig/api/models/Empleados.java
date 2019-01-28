/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.systig.api.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import javax.persistence.*;


/**
 *
 * @author Administracion
 */
@Entity
@Table(name = "empleados")
public class Empleados {

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
    @Basic(optional = false)
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Lob
    @Column(name = "profesion")
    private String profesion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "tasaHora")
    private BigDecimal tasaHora;
    @Basic(optional = false)
    @Column(name = "horasDia")
    private int horasDia;
    @Basic(optional = false)
    @Column(name = "moneda")
    private String moneda;
    @Basic(optional = false)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Basic(optional = false)
    @Lob
    @Column(name = "pais")
    private String pais;
    @Basic(optional = false)
    @Lob
    @Column(name = "provincia")
    private String provincia;
    @Basic(optional = false)
    @Lob
    @Column(name = "ciudad")
    private String ciudad;
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
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadosId", fetch = FetchType.LAZY)
    private List<ServiciosHasEmpleados> serviciosHasEmpleadosCollection;
    @JoinColumn(name = "empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empresas empresasId;

    public Empleados() {
    }

    public Empleados(Integer id) {
        this.id = id;
    }

    public Empleados(Integer id, String rif, Date fechaNacimiento, BigDecimal tasaHora, int horasDia, String moneda, String nacionalidad, String pais, String provincia, String ciudad, String email, String telMovil) {
        this.id = id;
        this.rif = rif;
        this.fechaNacimiento = fechaNacimiento;
        this.tasaHora = tasaHora;
        this.horasDia = horasDia;
        this.moneda = moneda;
        this.nacionalidad = nacionalidad;
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.email = email;
        this.telMovil = telMovil;
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

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public BigDecimal getTasaHora() {
        return tasaHora;
    }

    public void setTasaHora(BigDecimal tasaHora) {
        this.tasaHora = tasaHora;
    }

    public int getHorasDia() {
        return horasDia;
    }

    public void setHorasDia(int horasDia) {
        this.horasDia = horasDia;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<ServiciosHasEmpleados> getServiciosHasEmpleadosCollection() {
        return serviciosHasEmpleadosCollection;
    }

    public void setServiciosHasEmpleadosCollection(List<ServiciosHasEmpleados> serviciosHasEmpleadosCollection) {
        this.serviciosHasEmpleadosCollection = serviciosHasEmpleadosCollection;
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
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleados{" +
                "id=" + id +
                ", rif='" + rif + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", sexo='" + sexo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", profesion='" + profesion + '\'' +
                ", tasaHora=" + tasaHora +
                ", horasDia=" + horasDia +
                ", moneda='" + moneda + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", pais='" + pais + '\'' +
                ", provincia='" + provincia + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", email='" + email + '\'' +
                ", telMovil='" + telMovil + '\'' +
                ", puntos=" + puntos +
                ", fallas=" + fallas +
                ", fechaRegistro=" + fechaRegistro +
                ", serviciosHasEmpleadosCollection=" + serviciosHasEmpleadosCollection +
                ", empresasId=" + empresasId +
                '}';
    }
}
