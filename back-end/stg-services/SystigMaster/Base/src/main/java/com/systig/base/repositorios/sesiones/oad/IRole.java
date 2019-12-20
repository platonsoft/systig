package com.systig.base.repositorios.sesiones.oad;


import com.systig.base.repositorios.sesiones.entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRole extends JpaRepository<Rol,Long> {
}
