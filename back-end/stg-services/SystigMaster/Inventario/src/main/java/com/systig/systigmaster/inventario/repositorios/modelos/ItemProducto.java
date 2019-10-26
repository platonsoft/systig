package com.systig.systigmaster.inventario.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class ItemProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idItemProducto;
    private Long idDocumento;
    private String serial;
    private String unidad;
    private BigDecimal cantidad;
    private BigDecimal monto;
}
