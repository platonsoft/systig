package com.systig.systigmaster.clientes.repositorios.modelos.configuracion;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ObjConfiguracion {
    private Long fechaRegistro;
    private Long numeroTerminales;
    private Boolean isRetentor;
    private BigDecimal valorPuntos;
    private List<FormatoDocumento> formatoDocumento = new ArrayList<>();

}
