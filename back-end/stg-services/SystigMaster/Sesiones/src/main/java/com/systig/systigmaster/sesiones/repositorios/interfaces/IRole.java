package com.systig.systigmaster.sesiones.repositorios.interfaces;

import com.systig.systigmaster.sesiones.repositorios.modelos.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRole extends JpaRepository<Rol,Long> {
}
