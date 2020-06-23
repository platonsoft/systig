package com.systig.systigmaster.inventario.servicios.interfaces;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.contable.entidades.Documento;
import com.systig.base.repositorios.inventario.entidades.ItemProducto;
import com.systig.base.repositorios.inventario.entidades.Producto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductosServ {

    ResponseEntity<ResultadoTransaccion> getListadoMisProductos(HttpHeaders headers);
    ResponseEntity<ResultadoTransaccion> getListadoProductos(HttpHeaders headers);
    ResponseEntity<ResultadoTransaccion> getListadoProductosProveedor(HttpHeaders headers, Long idProveedor);
    ResponseEntity<ResultadoTransaccion> getListadoMisProductosProveedor(HttpHeaders headers, Long idProveedor);
    ResponseEntity<ResultadoTransaccion> getListadoProductosDocumento(HttpHeaders headers, Long idDocumento);
    ResponseEntity<ResultadoTransaccion> getProducto(HttpHeaders headers, Long idProducto);
    ResponseEntity<ResultadoTransaccion> addProducto(HttpHeaders headers, Producto producto);
    ResponseEntity<ResultadoTransaccion> addProductosItems(HttpHeaders headers, String strPeticion, Long idProducto);
    ResponseEntity<ResultadoTransaccion> setProducto(HttpHeaders headers, Producto producto, Long idProducto);
    ResponseEntity<ResultadoTransaccion> delProducto(HttpHeaders headers, Long idProducto);

    ResponseEntity<ResultadoTransaccion> getListadoAlmacenPropietario(HttpHeaders headers);
    ResponseEntity<ResultadoTransaccion> getListadoCategoriaPropietario(HttpHeaders headers);

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
