package com.systig.base.repositorios.nominas.oad;

import com.systig.base.repositorios.nominas.entidades.CuentaEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuentaEntidadDao extends JpaRepository<CuentaEntidad, Long> {
}
