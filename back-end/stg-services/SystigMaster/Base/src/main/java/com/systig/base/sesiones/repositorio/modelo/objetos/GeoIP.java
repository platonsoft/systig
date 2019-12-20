package com.systig.base.sesiones.repositorio.modelo.objetos;

import lombok.Data;

@Data
public class GeoIP {
    private String ipAddress;
    private String city;
    private String country;
    private String latitude;
    private String longitude;
}
