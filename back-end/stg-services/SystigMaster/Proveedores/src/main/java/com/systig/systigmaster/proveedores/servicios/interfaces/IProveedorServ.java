package com.systig.systigmaster.proveedores.servicios.interfaces;

import com.systig.base.repositorios.proveedores.entidades.Proveedor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface IProveedorServ {

    ResponseEntity<?> getListadoLigeroClientes(HttpHeaders headers, HttpSession session);

    ResponseEntity<?> getListadoLigeroProveedores(HttpHeaders headers, HttpSession session);

    ResponseEntity<?> getProveedor(HttpHeaders headers);

    ResponseEntity<?> getCliente(HttpHeaders headers, String tipoIdentificaionAbrev, String nroIdentificacion);

    ResponseEntity<?> nuevoProveedor(HttpHeaders headers, String tipoIdentificaionAbrev, String nroIdentificacion);

    ResponseEntity<?> actualizarProveedor(HttpHeaders headers, Proveedor proveedor, Long idProveedor);

    ResponseEntity<?> borrarProveedor(HttpHeaders headers, HttpSession session, Long idProveedor);

    ResponseEntity<?> getHistoriaProveedor(HttpHeaders headers, HttpSession session, Long idProveedor);
}
