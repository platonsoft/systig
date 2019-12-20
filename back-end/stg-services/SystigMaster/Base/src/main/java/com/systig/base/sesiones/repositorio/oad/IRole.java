package com.systig.base.sesiones.repositorio.oad;


import com.systig.base.sesiones.repositorio.modelo.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRole extends JpaRepository<Rol,Long> {
}
