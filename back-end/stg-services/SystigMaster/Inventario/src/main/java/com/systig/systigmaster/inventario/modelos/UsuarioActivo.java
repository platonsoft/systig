package com.systig.systigmaster.inventario.modelos;

import lombok.Data;

import java.util.Date;

@Data
public class UsuarioActivo extends User{
    private Integer codigoSystig;
    private String rol;
    private Integer nivelAcceso;
    private String ipRemota;
    private String sessionId;
    private Date fecha = new Date();
}
