package com.systig.base.repositorios.pay.entidades;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "STG_PAY_TASAS")
public class Tasa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTasas;
    private String codMoneda;
    private BigDecimal avg_24h;
    private BigDecimal avg_12h;
    private BigDecimal avg_6h;
    private BigDecimal avg_1h;
    private BigDecimal volume_btc;
    private BigDecimal rates_last;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fecha;
}
