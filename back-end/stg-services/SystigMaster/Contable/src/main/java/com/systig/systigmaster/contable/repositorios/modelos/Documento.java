package com.systig.systigmaster.contable.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "STG_CONT_DOCUMENTOS")
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
