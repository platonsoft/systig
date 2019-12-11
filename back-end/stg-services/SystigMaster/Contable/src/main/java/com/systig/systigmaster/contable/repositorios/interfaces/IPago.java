package com.systig.systigmaster.contable.repositorios.interfaces;

import com.systig.systigmaster.contable.repositorios.modelos.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPago extends JpaRepository<Pago, Long> {
}
