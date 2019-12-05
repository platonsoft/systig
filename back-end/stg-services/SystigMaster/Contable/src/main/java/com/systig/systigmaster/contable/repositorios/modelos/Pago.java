package com.systig.systigmaster.contable.repositorios.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "STG_CONT_PAGO")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPago;

    @JoinColumn(name = "id_forma_pago", referencedColumnName = "idFormaPago", nullable = false)
    @ManyToOne()
    private FormaPago formaPago;

    @JoinColumn(name = "id_metodo_pago", referencedColumnName = "idMetodoPago", nullable = false)
    @ManyToOne()
    private MetodoPago metodoPago;
    private Boolean isConfirmado;
    private BigDecimal monto;

    @JoinColumn(name="idDocumento")
    @ManyToOne(fetch = FetchType.LAZY, optional=false, cascade = CascadeType.ALL, targetEntity = Documento.class)
    @JsonBackReference
    private Documento idDocumento;
}
