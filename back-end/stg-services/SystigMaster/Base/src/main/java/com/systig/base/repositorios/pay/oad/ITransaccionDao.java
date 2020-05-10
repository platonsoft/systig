package com.systig.base.repositorios.pay.oad;

import com.systig.base.repositorios.pay.entidades.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransaccionDao extends JpaRepository<Transaccion, Long> {
    List<Transaccion> findAllByIdRemitenteOrIdClienteEquals(String idRemitente, String idCliente);
    Transaccion getByIdTransaccionEquals(Long idTransaccion);
}
