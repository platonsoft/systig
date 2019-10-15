package com.systig.systigmaster.repositorios.modelos.compradores;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_INV_COMPRADORES")
public class Comprador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idComprador;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String razonSocial;
    private String tipoCliente;
    private String telefonoLocal;
    private String telefonoMovil;
    private String email;
    private String direccionFiscal;
    private String codigoPostal;
    private String ciudad;
    private String provincia;
    private String pais;
    private String username;
    @JoinColumn(name = "id_etapa", referencedColumnName = "idEtapa", nullable = false)
    @OneToOne()
    private Etapa etapa;
    @JoinColumn(name = "id_campana", referencedColumnName = "idCampana", nullable = false)
    @ManyToOne()
    private CampanaPublicidad campanaPublicidad;
    private Long ranking;
}
