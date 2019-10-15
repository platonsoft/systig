package com.systig.systigmaster.repositorios.interfaces.usuarios;

import com.systig.systigmaster.repositorios.modelos.usuarios.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPropietarioDao extends JpaRepository<Propietario, Long> {

}
