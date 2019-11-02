package com.systig.systigmaster.sesiones.repositorios.interfaces;

import com.systig.systigmaster.sesiones.repositorios.modelos.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPropietarioDao extends JpaRepository<Propietario, Long> {

}
