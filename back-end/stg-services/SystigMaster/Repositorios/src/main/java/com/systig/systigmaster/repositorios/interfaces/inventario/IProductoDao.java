package com.systig.systigmaster.repositorios.interfaces.inventario;

import com.systig.systigmaster.repositorios.modelos.inventario.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoDao extends JpaRepository<Producto, Long> {
    List<Producto> findAllByPropietarioIdPropietario(Long idPropietario);
    List<Producto> findAllByIdProduto(Long idProducto);
}
