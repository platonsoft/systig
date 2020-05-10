package com.systig.base.repositorios.nominas.oad;

import com.systig.base.repositorios.nominas.entidades.EmpresaXPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpresaXPersonaDao extends JpaRepository<EmpresaXPersona, Long> {
}
