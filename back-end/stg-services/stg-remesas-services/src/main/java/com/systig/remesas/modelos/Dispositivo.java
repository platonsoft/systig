package com.systig.remesas.modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Dispositivo {
    @Id
    private Long imei;
    private String version;
    private Long latitud;
    private Long longitud;
    private String status;
    private String pais;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

    @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario", nullable = false,foreignKey = @ForeignKey(name = "FK_U_D",value = ConstraintMode.CONSTRAINT))
    @ManyToOne(optional = false)
    private Usuario usuarioId = new Usuario();

    public Dispositivo() {
    }

    @Override
    public String toString() {
        return "Dispositivo{" +
                "imei=" + imei +
                ", version='" + version + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", status='" + status + '\'' +
                ", pais='" + pais + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", idUsuario=" + usuarioId +
                '}';
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getLatitud() {
        return latitud;
    }

    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    public Long getLongitud() {
        return longitud;
    }

    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }
}
