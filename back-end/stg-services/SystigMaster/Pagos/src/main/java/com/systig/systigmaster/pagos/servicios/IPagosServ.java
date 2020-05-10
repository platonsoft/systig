package com.systig.systigmaster.pagos.servicios;

import com.systig.base.repositorios.pay.entidades.Transaccion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface IPagosServ {
    ResponseEntity<?> getListaTransacciones(HttpHeaders headers);
    ResponseEntity<?> getListaNotificaciones(HttpHeaders headers);
    ResponseEntity<?> getListaBancos(HttpHeaders headers);
    ResponseEntity<?> getTransaccion(Long idTransaccion, HttpHeaders headers);
    ResponseEntity<?> getTasas(HttpHeaders headers);
    ResponseEntity<?> setTransaccion(Transaccion transaccion, HttpHeaders headers);
    ResponseEntity<?> setTransaccion(Long idTransaccion, Long idAccion, HttpHeaders headers);
}
