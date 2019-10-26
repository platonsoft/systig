package com.systig.systigmaster.proveedores.repositorios.interfaces;

import com.systig.systigmaster.proveedores.repositorios.modelos.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProveedorDao extends JpaRepository<Proveedor,Long> {
    List findAllByIdPropietarioEquals(Long id_propietario);
    Proveedor getByIdProveedor(Long id_proveedor);
}
