package com.systig.base.repositorios.inventario.oad;

import com.systig.base.repositorios.inventario.entidades.ItemProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemProductoDao extends JpaRepository<ItemProducto, Long> {
    List<ItemProducto> findAllByIdDocumento_IdDocumento(Long idDocumento);
    List<ItemProducto> findAllByIdProducto_IdPropietario_IdEmpresa(Long idPropietario);
    List<ItemProducto> findAllByIdProducto_IdProveedor_IdEmpresaAndIdProducto_IdPropietario_IdEmpresa(Long idProveedor, Long idPropietario);
    List<ItemProducto> findAllByIdProducto_IdProveedor_IdEmpresa(Long idProveedor);

    List<ItemProducto> findAllByIsPublicoIsTrue();
}
