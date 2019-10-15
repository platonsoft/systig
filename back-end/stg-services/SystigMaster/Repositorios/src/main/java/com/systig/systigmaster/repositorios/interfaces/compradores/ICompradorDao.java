package com.systig.systigmaster.repositorios.interfaces.compradores;

import com.systig.systigmaster.repositorios.modelos.compradores.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompradorDao extends JpaRepository<Comprador, Long> {
    List<Comprador> findAllByUsernameEquals(String username);

    Comprador getByIdCompradorEquals(Long id_comprador);
}
