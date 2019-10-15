package com.systig.systigmaster.repositorios.modelos.compradores;

import com.systig.systigmaster.repositorios.modelos.usuarios.Propietario;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_INV_CAMPANAS_PUBLICIDAD")
public class CampanaPublicidad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCampana;
    private String titulo;
    private String descripcion;
    @JoinColumn(name = "id_propietario", referencedColumnName = "idPropietario", nullable = false)
    @ManyToOne()
    private Propietario propietario;
    private String alcance;
    private String ciudad;
    private String provincia;
    private String pais;
    private String username;
    private Long validaDesde;
    private Long validaHasta;
}
