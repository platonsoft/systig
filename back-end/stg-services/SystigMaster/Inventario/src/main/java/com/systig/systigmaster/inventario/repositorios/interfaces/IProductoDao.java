package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoDao extends JpaRepository<Producto, Long> {
    List<Producto> findAllByPropietarioIdPropietario(Long idPropietario);
    List<Producto> findAllByIdProduto(Long idProducto);
}
