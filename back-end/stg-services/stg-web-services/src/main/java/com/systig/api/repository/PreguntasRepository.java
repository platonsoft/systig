package com.systig.api.repository;

import com.systig.api.models.Preguntas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntasRepository extends JpaRepository<Preguntas, Integer> {
    Preguntas findByBase(boolean b);
}
