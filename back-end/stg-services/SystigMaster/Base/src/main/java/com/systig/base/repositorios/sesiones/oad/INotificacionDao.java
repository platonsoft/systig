package com.systig.base.repositorios.sesiones.oad;

import com.systig.base.repositorios.nominas.entidades.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INotificacionDao extends JpaRepository<Notificacion, Long> {
    List<Notificacion> findByIdPersona_IdPersona(Long idPersona);
}
