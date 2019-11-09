package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.repositorios.modelos.ItemProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemProductoDao extends JpaRepository<ItemProducto, Long> {
    List<ItemProducto> findAllByIdDocumentoEquals(Long idDocumento);
}
