package com.systig.systigmaster.proveedores.servicios.interfaces;

import com.systig.systigmaster.proveedores.repositorios.modelos.Proveedor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface IProveedorServ {
    ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session);

    ResponseEntity<?> getListadoLigero(HttpHeaders headers, HttpSession session);

    ResponseEntity<?> getProveedor(HttpHeaders headers, HttpSession session, Long idProveedor);

    ResponseEntity<?> nuevoProveedor(HttpHeaders headers, HttpSession session, Proveedor proveedor);

    ResponseEntity<?> actualizarProveedor(HttpHeaders headers, HttpSession session, Proveedor proveedor);

    ResponseEntity<?> borrarProveedor(HttpHeaders headers, HttpSession session, Long idComprador);

    ResponseEntity<?> getHistoriaProveedor(HttpHeaders headers, HttpSession session, Long idComprador);
}
