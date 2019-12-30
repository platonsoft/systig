package com.systig.base.repositorios.clientes.oad;

import com.systig.base.repositorios.clientes.entidades.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEtapaDao extends JpaRepository<Etapa,Long> {
    Etapa getFirstByNombreEquals(String nombre);
}
