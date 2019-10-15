package com.systig.systigmaster.repositorios.modelos.inventario;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_INV_CATEGORIAS")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private Long idPadre;
    private String username;
}
