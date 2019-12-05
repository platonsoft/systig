package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.repositorios.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoDao extends JpaRepository<Producto, Long> {
    List<Producto> findAllByIdPropietarioEquals(Long idPropietario);
    List<Producto> findAllByIdPropietarioEqualsAndIdProveedorEquals(Long idPropietario, Long idProveedor);
    List<Producto> getByIdProducto(Long idProducto);

}
