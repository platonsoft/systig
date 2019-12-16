package com.systig.systigmaster.sesiones.repositorios.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "STG_PRIVILEGIOS")
public class Privilegio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPrivilegio;

    @JoinColumn(name = "id_producto_systig", referencedColumnName = "idProductoSystig", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference(value = "producto-systig")
    private ProductoSystig productoSystig;
    private Boolean estatus;
    private Long fechaInicial;

    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario", nullable = false)
    @ManyToOne(optional = false)
    @JsonBackReference(value = "privilegios")
    private Usuario idUsuario;
    @Column(length = 6200)
    private String configuracion;
}
