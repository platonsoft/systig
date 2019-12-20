package com.systig.base.repositorios.contable.oad;

import com.systig.base.repositorios.contable.entidades.Historia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHistoriaDao extends JpaRepository<Historia, Long> {
    List<Historia> findAllByElementoEquals(String elemento);
}
