package com.systig.systigmaster.sesiones.repositorios.interfaces;

import com.systig.systigmaster.sesiones.repositorios.modelos.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConfiguracionDao extends JpaRepository<Configuracion,Long> {
}
