package com.systig.systigmaster.repositorios.interfaces.inventario;

import com.systig.systigmaster.repositorios.modelos.inventario.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlmacenDao extends JpaRepository<Almacen, Long> {

}
