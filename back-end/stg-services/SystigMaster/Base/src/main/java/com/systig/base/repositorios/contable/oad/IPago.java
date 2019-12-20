package com.systig.base.repositorios.contable.oad;

import com.systig.base.repositorios.contable.entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPago extends JpaRepository<Pago, Long> {
}
