package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.repositorios.modelos.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProveedorDao extends JpaRepository<Proveedor, Long> {
    List<Proveedor> findAllByIdPropietarioEquals(Long propietario);
}
