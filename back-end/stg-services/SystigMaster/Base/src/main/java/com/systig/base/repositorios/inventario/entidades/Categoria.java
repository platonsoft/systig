package com.systig.base.repositorios.inventario.entidades;

import com.systig.base.repositorios.nominas.entidades.Empresa;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_INV_CATEGORIAS")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private Long idPadre;

    @JoinColumn(name = "id_propietario", referencedColumnName = "id_empresa", nullable = false)
    @ManyToOne()
    private Empresa idPropietario;
}
