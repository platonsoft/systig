package com.systig.systigmaster.clientes.repositorios.interfaces;

import com.systig.systigmaster.clientes.modelos.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRole extends JpaRepository<Rol, Long> {
}
