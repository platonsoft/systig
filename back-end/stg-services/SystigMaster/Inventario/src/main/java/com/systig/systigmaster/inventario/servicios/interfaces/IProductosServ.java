package com.systig.systigmaster.inventario.servicios.interfaces;

import com.systig.systigmaster.inventario.repositorios.modelos.ItemProducto;
import com.systig.systigmaster.inventario.repositorios.modelos.Producto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface IProductosServ {

    ResponseEntity<?> getListadoProductos(HttpHeaders headers);
    ResponseEntity<?> getListadoProductosProveedor(HttpHeaders headers, Long idProveedor);
    ResponseEntity<?> getListadoProductosDocumento(HttpHeaders headers, Long idDocumento);
    ResponseEntity<?> getProducto(HttpHeaders headers, Long idProducto);
    ResponseEntity<?> addProducto(HttpHeaders headers, Producto producto);
    ResponseEntity<?> setProducto(HttpHeaders headers, Producto producto, Long idProducto);
    ResponseEntity<?> delProducto(HttpHeaders headers, Long idProducto);
    ResponseEntity<?> getHistoriaProducto(HttpHeaders headers, Long idProducto);

    ResponseEntity<?> getListadoAlmacenPropietario(HttpHeaders headers);
    ResponseEntity<?> getListadoCategoriaPropietario(HttpHeaders headers);

    enum TIPO_DOCUMENTO{
        FACTURA(1),
        NOTA_CREDITO(2),
        NOTA_DEBITO(3),
        NOTA_PEDIDO(4),
        NOTA_ENTREGA(5),
        NOTA_RECIBO(6),
        OTRO_DOCUMENTO(7);

        Integer tipoDocumento;

        TIPO_DOCUMENTO(Integer tipoDocumento){
            this.tipoDocumento = tipoDocumento;
        }

        public Integer getTipoDocumento() {
            return this.tipoDocumento;
        }
    }

}
