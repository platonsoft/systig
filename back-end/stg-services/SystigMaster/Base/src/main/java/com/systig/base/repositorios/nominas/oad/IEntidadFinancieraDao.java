package com.systig.base.repositorios.nominas.oad;

import com.systig.base.repositorios.nominas.entidades.EntidadFinanciera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEntidadFinancieraDao extends JpaRepository<EntidadFinanciera, Long> {
    List<EntidadFinanciera> findAllByPais_IdPais(Long idPais);
}
