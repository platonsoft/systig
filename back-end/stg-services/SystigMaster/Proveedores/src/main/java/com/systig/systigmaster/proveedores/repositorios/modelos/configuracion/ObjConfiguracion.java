package com.systig.systigmaster.proveedores.repositorios.modelos.configuracion;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ObjConfiguracion {
    private Long fechaRegistro;
    private Long numeroTerminales;
    private Boolean isRetentor;
    private List<FormatoDocumento> formatoDocumento = new ArrayList<>();

}
