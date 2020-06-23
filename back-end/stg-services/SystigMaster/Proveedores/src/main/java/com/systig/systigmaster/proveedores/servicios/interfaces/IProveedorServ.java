package com.systig.systigmaster.proveedores.servicios.interfaces;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.nominas.entidades.Empresa;
import com.systig.base.repositorios.proveedores.entidades.Proveedor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface IProveedorServ {

    ResponseEntity<ResultadoTransaccion> getListadoLigeroClientes(HttpHeaders headers, HttpSession session);

    ResponseEntity<ResultadoTransaccion> getListadoLigeroProveedores(HttpHeaders headers, HttpSession session);

    ResponseEntity<ResultadoTransaccion> getProveedor(HttpHeaders headers);

    ResponseEntity<ResultadoTransaccion> getCliente(HttpHeaders headers, String tipoIdentificaionAbrev, String nroIdentificacion);

    ResponseEntity<ResultadoTransaccion> nuevoProveedor(HttpHeaders headers, Empresa proveedor);

    ResponseEntity<ResultadoTransaccion> actualizarProveedor(HttpHeaders headers, Proveedor proveedor, Long idProveedor);

    ResponseEntity<ResultadoTransaccion> borrarProveedor(HttpHeaders headers, HttpSession session, Long idProveedor);

    ResponseEntity<ResultadoTransaccion> getHistoriaProveedor(HttpHeaders headers, HttpSession session, Long idProveedor);
}
