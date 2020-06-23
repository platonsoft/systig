package com.systig.base.repositorios.nominas.oad;

import com.systig.base.repositorios.nominas.entidades.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmpresaDao extends JpaRepository<Empresa, Long> {
    Empresa getFirstByTipoIdentificacion_AbrevDocAndNroIdentificacion(String tipoIdentificacion, String nroIdentificacion);
}
