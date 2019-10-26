package com.systig.systigmaster.contable.repositorios.interfaces;

import com.systig.systigmaster.contable.repositorios.modelos.Historia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHistoriaDao extends JpaRepository<Historia, Long> {
    List<Historia> findAllByElementoEquals(String elemento);
}
