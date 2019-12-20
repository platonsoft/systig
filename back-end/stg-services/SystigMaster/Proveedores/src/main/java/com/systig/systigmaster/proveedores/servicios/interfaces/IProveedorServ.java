package com.systig.systigmaster.proveedores.servicios.interfaces;

import com.systig.base.repositorios.proveedores.entidades.Proveedor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface IProveedorServ {

    ResponseEntity<?> getListadoLigero(HttpHeaders headers, HttpSession session);

    ResponseEntity<?> getProveedor(HttpHeaders headers, HttpSession session, Long idProveedor);

    ResponseEntity<?> nuevoProveedor(HttpHeaders headers, HttpSession session, Proveedor proveedor);

    ResponseEntity<?> actualizarProveedor(HttpHeaders headers, HttpSession session, Proveedor proveedor, Long idProveedor);

    ResponseEntity<?> borrarProveedor(HttpHeaders headers, HttpSession session, Long idProveedor);

    ResponseEntity<?> getHistoriaProveedor(HttpHeaders headers, HttpSession session, Long idProveedor);
}
