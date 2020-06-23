package com.systig.base.repositorios.sesiones.oad;

import com.systig.base.repositorios.sesiones.entidades.ProductoSystigXPropietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoSystigXPropietarioDao extends JpaRepository<ProductoSystigXPropietario, Long> {
}
