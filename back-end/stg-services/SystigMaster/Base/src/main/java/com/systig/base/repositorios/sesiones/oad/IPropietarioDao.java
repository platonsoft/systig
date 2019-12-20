package com.systig.base.repositorios.sesiones.oad;

import com.systig.base.repositorios.sesiones.entidades.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPropietarioDao extends JpaRepository<Propietario, Long> {

}
