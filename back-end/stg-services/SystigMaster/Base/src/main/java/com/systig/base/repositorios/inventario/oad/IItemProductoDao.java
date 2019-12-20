package com.systig.base.repositorios.inventario.oad;

import com.systig.base.repositorios.inventario.entidades.ItemProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemProductoDao extends JpaRepository<ItemProducto, Long> {
    List<ItemProducto> findAllByIdDocumentoEquals(Long idDocumento);
}
