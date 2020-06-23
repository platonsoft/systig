package com.systig.base.repositorios.inventario.oad;

import com.systig.base.repositorios.inventario.entidades.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlmacenDao extends JpaRepository<Almacen, Long> {
    List<Almacen> findAllByIdPropietario_IdEmpresa(Long idPropietario);
}
