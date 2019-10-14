package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.modelos.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRole extends JpaRepository<Rol,Long> {
}
