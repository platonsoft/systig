package com.systig.base.repositorios.inventario.oad;

import com.systig.base.repositorios.inventario.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoDao extends JpaRepository<Producto, Long> {

}
