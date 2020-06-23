package com.stg.systigpay.objetos;

import java.math.BigDecimal;

public class ItemTienda {
    private Long idItemTienda;
    private String nombreItem;
    private String nombreTienda;
    private BigDecimal precioUnitario;
    private BigDecimal IVA;
    private BigDecimal descuento;
    private MonedaPais monedaPais;
    private Boolean disponible;
    private Boolean seleccionado;

    public Long getIdItemTienda() {
        return idItemTienda;
    }

    public void setIdItemTienda(Long idItemTienda) {
        this.idItemTienda = idItemTienda;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getIVA() {
        return IVA;
    }

    public void setIVA(BigDecimal IVA) {
        this.IVA = IVA;
    }

    public MonedaPais getMonedaPais() {
        return monedaPais;
    }

    public void setMonedaPais(MonedaPais monedaPais) {
        this.monedaPais = monedaPais;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Boolean getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
