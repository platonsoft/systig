package com.systig.home.modelo;

public class Cliente {
    private Integer codigo;
    private Integer tipo;
    private Integer tipoIdentificacion;
    private Integer numeroIdentificacion;
    private Integer nombres;
    private Integer apellidos;
    private Integer razonSocial;
    private Integer telefLocal;
    private Integer telefonoMovil;
    private Integer email;
    private Integer direccionFiscal;
    private Integer codigoPostal;
    private Integer provincia;
    private Integer pais;

    public Cliente() {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Integer tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Integer getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(Integer numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Integer getNombres() {
        return nombres;
    }

    public void setNombres(Integer nombres) {
        this.nombres = nombres;
    }

    public Integer getApellidos() {
        return apellidos;
    }

    public void setApellidos(Integer apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(Integer razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Integer getTelefLocal() {
        return telefLocal;
    }

    public void setTelefLocal(Integer telefLocal) {
        this.telefLocal = telefLocal;
    }

    public Integer getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(Integer telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public Integer getEmail() {
        return email;
    }

    public void setEmail(Integer email) {
        this.email = email;
    }

    public Integer getDireccionFiscal() {
        return direccionFiscal;
    }

    public void setDireccionFiscal(Integer direccionFiscal) {
        this.direccionFiscal = direccionFiscal;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Integer getProvincia() {
        return provincia;
    }

    public void setProvincia(Integer provincia) {
        this.provincia = provincia;
    }

    public Integer getPais() {
        return pais;
    }

    public void setPais(Integer pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "codigo=" + codigo +
                ", tipo=" + tipo +
                ", tipoIdentificacion=" + tipoIdentificacion +
                ", numeroIdentificacion=" + numeroIdentificacion +
                ", nombres=" + nombres +
                ", apellidos=" + apellidos +
                ", razonSocial=" + razonSocial +
                ", telefLocal=" + telefLocal +
                ", telefonoMovil=" + telefonoMovil +
                ", email=" + email +
                ", direccionFiscal=" + direccionFiscal +
                ", codigoPostal=" + codigoPostal +
                ", provincia=" + provincia +
                ", pais=" + pais +
                '}';
    }
}
