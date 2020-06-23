package com.systig.base.repositorios.sesiones.oad;

import com.systig.base.repositorios.sesiones.entidades.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConfiguracionDao extends JpaRepository<Configuracion,Long> {
}
