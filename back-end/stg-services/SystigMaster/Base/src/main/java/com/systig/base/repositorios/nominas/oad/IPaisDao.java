package com.systig.base.repositorios.nominas.oad;

import com.systig.base.repositorios.nominas.entidades.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaisDao extends JpaRepository<Pais, Long> {
    Pais getByNombreEquals(String idPais);
}
