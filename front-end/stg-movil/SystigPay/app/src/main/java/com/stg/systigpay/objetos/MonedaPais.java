package com.stg.systigpay.objetos;

public class MonedaPais {
    private Long idMonedaPais;
    private Moneda moneda;
    private Pais pais;
    private Boolean disponible;

    public Long getIdMonedaPais() {
        return idMonedaPais;
    }

    public void setIdMonedaPais(Long idMonedaPais) {
        this.idMonedaPais = idMonedaPais;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
