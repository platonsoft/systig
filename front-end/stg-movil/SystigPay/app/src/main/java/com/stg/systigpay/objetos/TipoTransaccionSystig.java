package com.stg.systigpay.objetos;

public class TipoTransaccionSystig {
    private Long idTipoTransaccionSystig;
    private String nombre;
    private String descripcion;

    public Long getIdTipoTransaccionSystig() {
        return idTipoTransaccionSystig;
    }

    public void setIdTipoTransaccionSystig(Long idTipoTransaccionSystig) {
        this.idTipoTransaccionSystig = idTipoTransaccionSystig;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
