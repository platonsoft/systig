package com.systig.remesas.modelos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INotificacion extends JpaRepository<Notificacion,Long> {
}
