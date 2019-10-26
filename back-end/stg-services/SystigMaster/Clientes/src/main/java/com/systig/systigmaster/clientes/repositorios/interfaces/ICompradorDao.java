package com.systig.systigmaster.clientes.repositorios.interfaces;

import com.systig.systigmaster.clientes.modelos.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompradorDao extends JpaRepository<Comprador, Long> {
    List<Comprador> findAllByUsernameEquals(String username);

    Comprador getByIdCompradorEquals(Long id_comprador);
    Comprador getByNumeroIdentificacionContains(String numeroIdentificacion);
}
