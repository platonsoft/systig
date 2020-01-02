package com.systig.base.repositorios.sesiones.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "STG_PRODUCTO_STG_X_PROPIETARIO")
public class ProductoSystigXPropietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductoSystigXPropietario;

    @JoinColumn(name = "id_producto_systig", referencedColumnName = "id_producto_systig")
    @ManyToOne()
    private ProductoSystig productoSystig;

    @OneToMany( mappedBy = "productoSystigXPropietario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PagosSystig> pagosSystig = new ArrayList<>();

    @JoinColumn(name = "id_propietario", referencedColumnName = "id_propietario")
    @ManyToOne()
    @JsonIgnore
    private Propietario idPropietario;
}
