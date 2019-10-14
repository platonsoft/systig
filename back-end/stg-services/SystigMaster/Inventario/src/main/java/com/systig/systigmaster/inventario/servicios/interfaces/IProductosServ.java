package com.systig.systigmaster.inventario.servicios.interfaces;

import com.systig.systigmaster.inventario.modelos.Producto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface IProductosServ {

    ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session);
    ResponseEntity<?> getListadoLigero(HttpHeaders headers, HttpSession session);
    ResponseEntity<?> getProducto(HttpHeaders headers, HttpSession session, Long idProducto);
    ResponseEntity<?> nuevoProducto(HttpHeaders headers, HttpSession session, Producto producto);
    ResponseEntity<?> actualizarProducto(HttpHeaders headers, HttpSession session, Producto producto);
    ResponseEntity<?> borrarProducto(HttpHeaders headers, HttpSession session, Long idProducto);
    ResponseEntity<?> getHistoriaProducto(HttpHeaders headers, HttpSession session, Long idProducto);

}
