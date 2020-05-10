package com.systig.base.repositorios.clientes.oad;

import com.systig.base.repositorios.clientes.entidades.Comprador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompradorDao extends JpaRepository<Comprador, Long> {
    List<Comprador> findAllByIdEmpresaEquals(Long idEmpresa);
    Comprador getByNroIdentificacionEquals(String numeroIdentificacion);
}
