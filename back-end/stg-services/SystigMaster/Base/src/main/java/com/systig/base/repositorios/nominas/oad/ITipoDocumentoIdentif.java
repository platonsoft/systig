package com.systig.base.repositorios.nominas.oad;

import com.systig.base.repositorios.nominas.entidades.TipoDocumentoIdentif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoDocumentoIdentif extends JpaRepository<TipoDocumentoIdentif, Long> {
}
