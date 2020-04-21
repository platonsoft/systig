package com.stg.flashpay;

public class Usuario {
    private String codigo;
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String nroDocumento;
    private String email;
    private String telefono;
    private String fechaNacimiento;

    private String pais;
    private String entidadBancaria;
    private String numeroCuentaBancaria;
    private String tipoCuentaBancaria;

    private String usuario;
    private String clave;
    private Integer pin;

    private String fotoX64;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEntidadBancaria() {
        return entidadBancaria;
    }

    public void setEntidadBancaria(String entidadBancaria) {
        this.entidadBancaria = entidadBancaria;
    }

    public String getNumeroCuentaBancaria() {
        return numeroCuentaBancaria;
    }

    public void setNumeroCuentaBancaria(String numeroCuentaBancaria) {
        this.numeroCuentaBancaria = numeroCuentaBancaria;
    }

    public String getTipoCuentaBancaria() {
        return tipoCuentaBancaria;
    }

    public void setTipoCuentaBancaria(String tipoCuentaBancaria) {
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getFotoX64() {
        return fotoX64;
    }

    public void setFotoX64(String fotoX64) {
        this.fotoX64 = fotoX64;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "codigo=" + codigo +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", nroDocumento=" + nroDocumento +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", pais='" + pais + '\'' +
                ", entidadBancaria='" + entidadBancaria + '\'' +
                ", numeroCuentaBancaria='" + numeroCuentaBancaria + '\'' +
                ", tipoCuentaBancaria='" + tipoCuentaBancaria + '\'' +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", pin=" + pin +
                ", fotoX64='" + fotoX64 + '\'' +
                '}';
    }

}
