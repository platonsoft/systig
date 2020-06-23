package com.systig.systigmaster.clientes.servicios.interfaces;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.clientes.entidades.Comprador;
import com.systig.base.repositorios.nominas.entidades.Persona;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface ICompradorServ {

    ResponseEntity<ResultadoTransaccion> getListadoLigero(HttpHeaders headers, HttpSession session);

    ResponseEntity<ResultadoTransaccion> getComprador(HttpHeaders headers, HttpSession session, Long idComprador);

    ResponseEntity<ResultadoTransaccion> getComprador(HttpHeaders headers, HttpSession session, String campoFiltro, String numeroIdentificacion);

    ResponseEntity<ResultadoTransaccion> nuevoComprador(HttpHeaders headers, HttpSession session, Persona clientePersona);

    ResponseEntity<ResultadoTransaccion> actualizarComprador(HttpHeaders headers, HttpSession session, Persona clientePersona, String nroIdentificacion);

    ResponseEntity<ResultadoTransaccion> siguienteEtapaComprador(HttpHeaders headers, HttpSession session, Long id_comprador);

    ResponseEntity<ResultadoTransaccion> anteriorEtapaComprador(HttpHeaders headers, HttpSession session, Long id_comprador);

    ResponseEntity<ResultadoTransaccion> asignarCampanaComprador(HttpHeaders headers, HttpSession session, Long id_comprador, Long id_campana);

    ResponseEntity<ResultadoTransaccion> borrarComprador(HttpHeaders headers, HttpSession session, Long idComprador);

    ResponseEntity<ResultadoTransaccion> getHistoriaComprador(HttpHeaders headers, HttpSession session, Long idComprador);
}
