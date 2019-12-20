package com.systig.systigmaster.inventario.servicios.interfaces;

import com.systig.base.repositorios.inventario.entidades.ItemProducto;
import com.systig.base.repositorios.inventario.entidades.Producto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IItemProductosServ {
    ResponseEntity<?> getListadoItemsProductoDocumento(HttpHeaders headers, Long idDocumento);
    ResponseEntity<?> addItemProducto(HttpHeaders headers, ItemProducto itemProducto, Long idProducto, Long idDocumento);
    ResponseEntity<?> addItemsDocumento(HttpHeaders headers, List<Producto> productos, Long idDocumento);
    ResponseEntity<?> UpdateItemProducto(HttpHeaders headers, Long idItemProducto, Long idDocumentoNuevo);
    ResponseEntity<?> delItemProducto(HttpHeaders headers, Long idItemProducto, String observacion);
    ResponseEntity<?> getHistoriaItemProducto(HttpHeaders headers, Long idItemProducto);
}
