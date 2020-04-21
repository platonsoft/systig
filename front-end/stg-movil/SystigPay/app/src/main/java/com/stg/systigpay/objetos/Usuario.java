package com.stg.systigpay.objetos;

import java.util.Date;

public class Usuario {
    private Long idUsuario;
    private String codigoDispositivo;
    private String cuentaSystig;
    private TipoCliente tipoCliente;
    private TipoIdentificacion tipoIdenficacion;
    private Integer nroIdentificacion;
    private String nombres;
    private String apellidos;
    private String razonSocial;
    private String nroMovil;
    private String direccion;
    private String email;
    private String clave;
    private String pin;
    private MonedaPais monedaPais;
    private TransaccionSystig[] transacciones;
    private Notificacion[] notificaciones;
    private String foto64;
    private Date fechaRegistro;
    private Status status;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigoDispositivo() {
        return codigoDispositivo;
    }

    public void setCodigoDispositivo(String codigoDispositivo) {
        this.codigoDispositivo = codigoDispositivo;
    }

    public String getCuentaSystig() {
        return cuentaSystig;
    }

    public void setCuentaSystig(String cuentaSystig) {
        this.cuentaSystig = cuentaSystig;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public TipoIdentificacion getTipoIdenficacion() {
        return tipoIdenficacion;
    }

    public void setTipoIdenficacion(TipoIdentificacion tipoIdenficacion) {
        this.tipoIdenficacion = tipoIdenficacion;
    }

    public Integer getNroIdentificacion() {
        return nroIdentificacion;
    }

    public void setNroIdentificacion(Integer nroIdentificacion) {
        this.nroIdentificacion = nroIdentificacion;
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNroMovil() {
        return nroMovil;
    }

    public void setNroMovil(String nroMovil) {
        this.nroMovil = nroMovil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public MonedaPais getMonedaPais() {
        return monedaPais;
    }

    public void setMonedaPais(MonedaPais monedaPais) {
        this.monedaPais = monedaPais;
    }

    public TransaccionSystig[] getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(TransaccionSystig[] transacciones) {
        this.transacciones = transacciones;
    }

    public Notificacion[] getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(Notificacion[] notificaciones) {
        this.notificaciones = notificaciones;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFoto64() {
        return foto64;
    }

    public void setFoto64(String foto64) {
        this.foto64 = foto64;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
