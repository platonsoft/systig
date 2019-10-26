package com.systig.systigmaster.inventario.servicios.interfaces;

import com.systig.systigmaster.inventario.repositorios.modelos.Producto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface IProductosServ {

    ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session);
    ResponseEntity<?> getListadoProductos(HttpHeaders headers, HttpSession session);
    ResponseEntity<?> getListadoProductosDocumento(HttpHeaders headers, HttpSession session, Long idDocumento);
    ResponseEntity<?> getProducto(HttpHeaders headers, HttpSession session, Long idProducto);
    ResponseEntity<?> addProducto(HttpHeaders headers, HttpSession session, Producto producto);
    ResponseEntity<?> setProducto(HttpHeaders headers, HttpSession session, Producto producto);
    ResponseEntity<?> delProducto(HttpHeaders headers, HttpSession session, Long idProducto);
    ResponseEntity<?> getHistoriaProducto(HttpHeaders headers, HttpSession session, Long idProducto);

}
