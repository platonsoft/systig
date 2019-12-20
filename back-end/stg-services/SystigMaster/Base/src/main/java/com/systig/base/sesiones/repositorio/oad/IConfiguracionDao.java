package com.systig.base.sesiones.repositorio.oad;

import com.systig.base.sesiones.repositorio.modelo.entidades.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConfiguracionDao extends JpaRepository<Configuracion,Long> {
}
