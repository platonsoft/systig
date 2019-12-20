package com.systig.base.repositorios.contable.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Data
@Entity
@Table(name = "STG_CONT_DOCUMENTOS")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;
    private Integer tipoDocumento;
    private String nroControl;
    private String Codigo;
    private Long idCliente;
    private Long idProveedor;
    private Long fechaCreacion;
    private Long fechaAprobacion;
    private BigInteger validez;
    private Long idPropietario;
    /*
    * Los estados serian los siguientes:
    * Estados para Pedidos
    * 0 - pedido pendiente por aprovacion
    * 1 - pedido aprobado pagado y pendiente por recepcion
    * 2 - pedido recibido
    *
    * */
    private Long Estado;

    @OneToMany( mappedBy = "idPago", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Pago> pagos;
}
