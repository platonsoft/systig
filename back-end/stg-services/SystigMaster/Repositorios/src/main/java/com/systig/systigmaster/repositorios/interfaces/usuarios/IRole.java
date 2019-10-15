package com.systig.systigmaster.repositorios.interfaces.usuarios;

import com.systig.systigmaster.repositorios.modelos.usuarios.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRole extends JpaRepository<Rol,Long> {
}
