package com.systig.systigmaster.repositorios.interfaces.inventario;

import com.systig.systigmaster.repositorios.modelos.inventario.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaDao extends JpaRepository<Categoria, Long> {

}
