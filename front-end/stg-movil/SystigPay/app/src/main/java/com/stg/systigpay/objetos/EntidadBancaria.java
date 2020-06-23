package com.stg.systigpay.objetos;

public class EntidadBancaria {
    private Long idEntidadBancaria;
    private String nombre;
    private MonedaPais monedaPais;
    private Boolean disponible;

    public Long getIdEntidadBancaria() {
        return idEntidadBancaria;
    }

    public void setIdEntidadBancaria(Long idEntidadBancaria) {
        this.idEntidadBancaria = idEntidadBancaria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MonedaPais getMonedaPais() {
        return monedaPais;
    }

    public void setMonedaPais(MonedaPais monedaPais) {
        this.monedaPais = monedaPais;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }
}
