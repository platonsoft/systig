package com.systig.base.repositorios.sesiones.oad;

import com.systig.base.repositorios.sesiones.entidades.FormatoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormatoDocumentoDao extends JpaRepository<FormatoDocumento, Long> {
}
