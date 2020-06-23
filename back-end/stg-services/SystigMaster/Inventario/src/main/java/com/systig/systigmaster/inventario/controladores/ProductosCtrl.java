package com.systig.systigmaster.inventario.controladores;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.inventario.entidades.ItemProducto;
import com.systig.base.repositorios.inventario.entidades.Producto;
import com.systig.systigmaster.inventario.servicios.interfaces.IProductosServ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;


@RestController
@CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token","Authorization"})
@Api(tags = {"Inventario"})
public class ProductosCtrl {

    private final IProductosServ productosServ;

    public ProductosCtrl(IProductosServ productosServ) {
        this.productosServ = productosServ;
    }

    @GetMapping("/api/inv/productos")
    @ApiOperation(value = "Muestra los productos pertencientes al usuario")
    public ResponseEntity<ResultadoTransaccion> getListaProductos(@RequestHeader HttpHeaders headers) {
        return this.productosServ.getListadoProductos(headers);
    }

    @GetMapping("/api/inv/productos/proveedor/{id_proveedor}")
    @ApiOperation(value = "Muestra los productos pertencientes al usuario por proveedor")
    public ResponseEntity<ResultadoTransaccion> getListaProductos(@RequestHeader HttpHeaders headers,
                                               @PathVariable Long id_proveedor) {
        return this.productosServ.getListadoProductosProveedor(headers, id_proveedor);
    }

    @GetMapping("/api/inv/almacenes")
    @ApiOperation(value = "Muestra los almacenes registrados pertencientes al usuario")
    public ResponseEntity<ResultadoTransaccion> getListaAlmacenes(@RequestHeader HttpHeaders headers) {
        return this.productosServ.getListadoAlmacenPropietario(headers);
    }

    @GetMapping("/api/inv/categorias")
    @ApiOperation(value = "Muestra los categorias registradas pertencientes al usuario")
    public ResponseEntity<ResultadoTransaccion> getListaCategorias(@RequestHeader HttpHeaders headers) {
        return this.productosServ.getListadoCategoriaPropietario(headers);
    }

    @GetMapping("/api/inv/productos/documento/{id_documento}")
    @ApiOperation(value = "Muestra los productos asociados a un documento")
    public ResponseEntity<ResultadoTransaccion> getListaProductosDocumento(@RequestHeader HttpHeaders headers, @PathVariable Long id_documento) {
        return this.productosServ.getListadoProductosDocumento(headers,id_documento);
    }

    @GetMapping("/api/inv/producto/{id_producto}")
    @ApiOperation(value = "Muestra un productos a traves de su id pertencientes al usuario")
    public ResponseEntity<ResultadoTransaccion> getProducto(@RequestHeader HttpHeaders headers,
                                                @PathVariable Long id_producto) {
        return this.productosServ.getProducto(headers, id_producto);
    }

    @PostMapping(value = "/api/inv/producto")
    @ApiOperation(value = "Agrega un nuevo producto")
    public ResponseEntity<ResultadoTransaccion> agregarProducto(@RequestHeader HttpHeaders headers,
                                                @RequestBody Producto producto) {
        return this.productosServ.addProducto(headers,producto);
    }

    @PostMapping(value = "/api/inv/producto/items/{id_documento}")
    @ApiOperation(value = "Asocia productos a un documento")
    public ResponseEntity<ResultadoTransaccion> agregarItemsDocumento(@RequestHeader HttpHeaders headers,
                                                 @RequestBody String peticion,
                                                 @PathVariable Long id_documento) {
        return this.productosServ.addProductosItems(headers,peticion,id_documento);
    }

    @PutMapping("/api/inv/producto/{id_producto}")
    @ApiOperation(value = "Actualiza la informacion de un producto")
    public ResponseEntity<ResultadoTransaccion> actualizarProducto(@RequestHeader HttpHeaders headers,
                                                  @RequestBody Producto producto,
                                                @PathVariable Long id_producto) {
        return this.productosServ.setProducto(headers,producto, id_producto);
    }

    @DeleteMapping("/api/inv/producto/{id_producto}")
    @ApiOperation(value = "Marca como borrado un producto")
    public ResponseEntity<ResultadoTransaccion> borrarProducto(@RequestHeader HttpHeaders headers,
                                                     @PathVariable Long id_producto) {
        return this.productosServ.delProducto(headers,id_producto);
    }

    @PostMapping(value = "/api/inv/producto/importar", consumes = {"multipart/form-data"})
    @ApiOperation(value = "Importa productos a partir de un archivo csv")
    public ResponseEntity<ResultadoTransaccion> importarArchivo(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) throws IOException {
        return null;
    }

    @GetMapping(value = "/api/inv/producto/exportar/XML", produces = { "application/xml", "text/xml" })
    @ApiOperation(value = "Exportar los productos en formato xml")
    public ResponseEntity<ResultadoTransaccion> exportarXML() {
        return null;
    }

}
