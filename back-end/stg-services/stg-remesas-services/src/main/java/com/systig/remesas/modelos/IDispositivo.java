package com.systig.remesas.modelos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDispositivo extends JpaRepository<Dispositivo,Long> {
}
