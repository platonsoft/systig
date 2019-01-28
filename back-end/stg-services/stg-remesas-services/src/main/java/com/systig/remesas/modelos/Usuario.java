package com.systig.remesas.modelos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "US_REMIT"))
    private Remitente datosPersonales;
    private String usuario;
    private String clave;
    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal montoCorte;
    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal saldo;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;
    @Column(columnDefinition="TIMESTAMP default null")
    private LocalDateTime fechaRetiro;

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", datosPersonales=" + datosPersonales +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", montoCorte=" + montoCorte +
                ", saldo=" + saldo +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaRetiro=" + fechaRetiro +
                '}';
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Remitente getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(Remitente datosPersonales) {
        this.datosPersonales = datosPersonales;
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

    public BigDecimal getMontoCorte() {
        return montoCorte;
    }

    public void setMontoCorte(BigDecimal montoCorte) {
        this.montoCorte = montoCorte;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(LocalDateTime fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }
}
