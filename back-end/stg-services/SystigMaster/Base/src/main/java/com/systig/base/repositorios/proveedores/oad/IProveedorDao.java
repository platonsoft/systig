package com.systig.base.repositorios.proveedores.oad;

import com.systig.base.repositorios.proveedores.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProveedorDao extends JpaRepository<Proveedor,Long> {
    List<Proveedor> findAllByEmpresa_IdEmpresa(Long idEmpresa);
    List<Proveedor> findAllByCliente_IdEmpresa(Long idEmpresa);
    Proveedor getFirstByEmpresa_NroIdentificacionAndEmpresa_TipoIdentificacionAbrevDoc(String nroIdentificacion, String tipoIdentificacion);
    Proveedor getFirstByCliente_NroIdentificacionAndCliente_TipoIdentificacionAbrevDocAndCliente_IdEmpresa(String nroIdentificacion, String tipoIdentificacion, Long idEmpresa);
}
