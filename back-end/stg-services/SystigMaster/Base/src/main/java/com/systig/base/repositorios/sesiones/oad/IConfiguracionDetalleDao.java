package com.systig.base.repositorios.sesiones.oad;

import com.systig.base.repositorios.sesiones.entidades.ConfiguracionDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConfiguracionDetalleDao extends JpaRepository<ConfiguracionDetalle, Long> {
}
