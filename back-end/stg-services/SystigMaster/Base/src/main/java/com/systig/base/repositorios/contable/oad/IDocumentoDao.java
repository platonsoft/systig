package com.systig.base.repositorios.contable.oad;

import com.systig.base.repositorios.contable.entidades.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDocumentoDao extends JpaRepository<Documento, Long> {
    List<Documento> findAllByTipoDocumentoEqualsAndIdPropietarioEquals(Integer tipoDocumento,Long idPropietario);
    List<Documento> findAllByIdPropietarioEquals(Long idPropietario);
    Documento findByIdDocumentoEquals(Long idDocumento);
}
