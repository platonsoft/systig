package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.repositorios.modelos.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlmacenDao extends JpaRepository<Almacen, Long> {
    List<Almacen> findAllByIdPropietarioEquals(Long propietario);
}
