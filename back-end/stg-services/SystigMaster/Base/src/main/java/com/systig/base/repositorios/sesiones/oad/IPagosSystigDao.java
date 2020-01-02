package com.systig.base.repositorios.sesiones.oad;

import com.systig.base.repositorios.sesiones.entidades.PagosSystig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagosSystigDao extends JpaRepository<PagosSystig, Long> {
}
