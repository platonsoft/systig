package com.systig.systigmaster.clientes.repositorios.interfaces;

import com.systig.systigmaster.clientes.repositorios.modelos.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompradorDao extends JpaRepository<Comprador, Long> {
    List<Comprador> findAllByIdPropietarioEquals(Long idPropietario);
    Comprador getByNumeroIdentificacionEquals(String numeroIdentificacion);
}
