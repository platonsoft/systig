package com.systig.remesas.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransaccion extends JpaRepository<Transaccion,Long> {
}
