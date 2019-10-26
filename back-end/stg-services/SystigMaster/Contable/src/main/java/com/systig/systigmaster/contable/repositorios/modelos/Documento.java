package com.systig.systigmaster.contable.repositorios.modelos;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDocumento;
    private Integer tipoDocumento;
    private String nroControl;
    private String Codigo;
    private Long idCliente;
    private LocalDateTime fecha;
    private BigInteger validez;
    private Long idPropietario;
    private Integer Estado;
}
