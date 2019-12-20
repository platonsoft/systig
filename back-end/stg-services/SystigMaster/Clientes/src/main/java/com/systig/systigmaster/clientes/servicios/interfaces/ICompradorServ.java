package com.systig.systigmaster.clientes.servicios.interfaces;

import com.systig.base.repositorios.clientes.entidades.Comprador;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface ICompradorServ {

    ResponseEntity<?> getListadoLigero(HttpHeaders headers, HttpSession session);

    ResponseEntity<?> getComprador(HttpHeaders headers, HttpSession session, Long idComprador);

    ResponseEntity<?> getComprador(HttpHeaders headers, HttpSession session, String campoFiltro, String numeroIdentificacion);

    ResponseEntity<?> nuevoComprador(HttpHeaders headers, HttpSession session, Comprador comprador);

    ResponseEntity<?> actualizarComprador(HttpHeaders headers, HttpSession session, Comprador comprador, Long idComprador);

    ResponseEntity<?> siguienteEtapaComprador(HttpHeaders headers, HttpSession session, Long id_comprador);

    ResponseEntity<?> anteriorEtapaComprador(HttpHeaders headers, HttpSession session, Long id_comprador);

    ResponseEntity<?> asignarCampanaComprador(HttpHeaders headers, HttpSession session, Long id_comprador, Long id_campana);

    ResponseEntity<?> borrarComprador(HttpHeaders headers, HttpSession session, Long idComprador);

    ResponseEntity<?> getHistoriaComprador(HttpHeaders headers, HttpSession session, Long idComprador);
}
