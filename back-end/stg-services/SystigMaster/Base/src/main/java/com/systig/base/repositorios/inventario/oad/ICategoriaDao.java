package com.systig.base.repositorios.inventario.oad;

import com.systig.base.repositorios.inventario.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoriaDao extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByIdPropietario_IdEmpresa(Long idPropietario);
}
