package com.systig.base.repositorios.nominas.entidades;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "STG_NOM_COMENTARIOS")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long idComentario;

    @JoinColumn(name = "comentador", referencedColumnName = "id_persona")
    @ManyToOne()
    private Persona comentador;

    @JoinColumn(name = "comentado", referencedColumnName = "id_persona")
    @ManyToOne()
    private Persona comentado;

    private String comentario;

    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;

    private Long rankBueno;
    private Long rankMalo;
}
