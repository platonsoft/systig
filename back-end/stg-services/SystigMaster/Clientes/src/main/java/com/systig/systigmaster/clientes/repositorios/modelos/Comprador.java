package com.systig.systigmaster.clientes.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_CLI_COMPRADORES")
public class Comprador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprador;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
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
    private String moneda;
    private String pais;
    private Long idPropietario;
    @JoinColumn(name = "id_etapa", referencedColumnName = "idEtapa")
    @OneToOne()
    private Etapa etapa;
    @JoinColumn(name = "id_campana", referencedColumnName = "idCampana")
    @ManyToOne()
    private CampanaPublicidad campanaPublicidad;
    private Long ranking;
}
