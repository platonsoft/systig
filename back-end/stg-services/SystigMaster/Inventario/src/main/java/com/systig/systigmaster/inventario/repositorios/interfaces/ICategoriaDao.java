package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.repositorios.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoriaDao extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByIdPropietarioEquals(Long propietario);
}
