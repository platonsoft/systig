package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.repositorios.modelos.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlmacenDao extends JpaRepository<Almacen, Long> {

}
