package com.systig.systigmaster.repositorios.interfaces.usuarios;

import com.systig.systigmaster.repositorios.modelos.inventario.Historia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHistoriaDao extends JpaRepository<Historia, Long> {
    List<Historia> findAllByElementoEquals(String elemento);
}
