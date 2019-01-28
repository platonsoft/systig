package com.alcala.systig.ltven;

import java.time.LocalDateTime;

public class Receptor {
    private Long id_receptor;
    private String tipo;//CUneta Interna, cuenta cliente
    private String tDoc;
    private String nroDoc;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private String pais;
    private Cuenta cuenta;
    private LocalDateTime fechaRegistro;

    public Receptor() {
    }

    public Long getId_receptor() {
        return id_receptor;
    }

    public void setId_receptor(Long id_receptor) {
        this.id_receptor = id_receptor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String gettDoc() {
        return tDoc;
    }

    public void settDoc(String tDoc) {
        this.tDoc = tDoc;
    }

    public String getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(String nroDoc) {
        this.nroDoc = nroDoc;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Receptor{" +
                "id_receptor=" + id_receptor +
                ", tipo='" + tipo + '\'' +
                ", tDoc='" + tDoc + '\'' +
                ", nroDoc='" + nroDoc + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", pais='" + pais + '\'' +
                ", cuenta=" + cuenta +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
