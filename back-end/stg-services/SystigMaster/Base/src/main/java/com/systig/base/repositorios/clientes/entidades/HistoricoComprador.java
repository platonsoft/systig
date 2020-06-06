package com.systig.base.repositorios.clientes.entidades;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "STG_CLI_HISTORICO_COMPRADOR")
public class HistoricoComprador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistoricoComprador;
    private String descripcion;

    @JoinColumn(name = "id_comprador", referencedColumnName = "idComprador")
    @ManyToOne()
    private Comprador comprador;

    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

}
