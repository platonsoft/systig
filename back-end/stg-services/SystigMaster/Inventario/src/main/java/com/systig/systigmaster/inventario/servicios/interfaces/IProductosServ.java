package com.systig.systigmaster.inventario.servicios.interfaces;

import com.systig.systigmaster.inventario.repositorios.modelos.Producto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface IProductosServ {

    ResponseEntity<?> getListadoProductos(HttpHeaders headers);
    ResponseEntity<?> getListadoProductosDocumento(HttpHeaders headers, Long idDocumento);
    ResponseEntity<?> getProducto(HttpHeaders headers, Long idProducto);
    ResponseEntity<?> addProducto(HttpHeaders headers, Producto producto);
    ResponseEntity<?> setProducto(HttpHeaders headers, Producto producto, Long idProducto);
    ResponseEntity<?> delProducto(HttpHeaders headers, Long idProducto);
    ResponseEntity<?> getHistoriaProducto(HttpHeaders headers, Long idProducto);

    ResponseEntity<?> getListadoAlmacenPropietario(HttpHeaders headers);
    ResponseEntity<?> getListadoProveedoresPropietario(HttpHeaders headers);
    ResponseEntity<?> getListadoCategoriaPropietario(HttpHeaders headers);

}
