package com.systig.systigmaster.contable.servicios.interfaces;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.nominas.entidades.Empresa;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface IEmpresaServ {
    ResponseEntity<ResultadoTransaccion> lista(HttpHeaders headers);

    ResponseEntity<ResultadoTransaccion> getEmpresa(HttpHeaders headers, Long idEmpresa);

    ResponseEntity<ResultadoTransaccion> getEmpresa(HttpHeaders headers, Long tipoDoc, String nroDoc);

    ResponseEntity<ResultadoTransaccion> nuevaEmpresa(HttpHeaders headers, Empresa empresa);

    ResponseEntity<ResultadoTransaccion> actualizarEmpresa(HttpHeaders headers, Empresa empresa);

    ResponseEntity<ResultadoTransaccion> borrarEmpresa(HttpHeaders headers, Long idEmpresa);
}
