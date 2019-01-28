package com.systig.remesas.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuenta extends JpaRepository<Cuenta,Long> {
}
