package com.systig.systigmaster.inventario.controladores;

import com.systig.base.repositorios.inventario.entidades.ItemProducto;
import com.systig.base.repositorios.inventario.entidades.Producto;
import com.systig.systigmaster.inventario.servicios.interfaces.IItemProductosServ;
import com.systig.systigmaster.inventario.servicios.interfaces.IProductosServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token","Authorization"})
public class ProductosCtrl {

    private final IProductosServ productosServ;
    private final IItemProductosServ itemProductosServ;

    public ProductosCtrl(IProductosServ productosServ, IItemProductosServ itemProductosServ) {
        this.productosServ = productosServ;
        this.itemProductosServ = itemProductosServ;
    }

    @GetMapping("/api/inv/productos")
    public ResponseEntity<?> getListaProductos(@RequestHeader HttpHeaders headers) {
        return this.productosServ.getListadoProductos(headers);
    }

    @GetMapping("/api/inv/productos/proveedor/{id_proveedor}")
    public ResponseEntity<?> getListaProductos(@RequestHeader HttpHeaders headers,
                                               @PathVariable Long id_proveedor) {
        return this.productosServ.getListadoProductosProveedor(headers, id_proveedor);
    }

    @GetMapping("/api/inv/almacenes")
    public ResponseEntity<?> getListaAlmacenes(@RequestHeader HttpHeaders headers) {
        return this.productosServ.getListadoAlmacenPropietario(headers);
    }

    @GetMapping("/api/inv/categorias")
    public ResponseEntity<?> getListaCategorias(@RequestHeader HttpHeaders headers) {
        return this.productosServ.getListadoCategoriaPropietario(headers);
    }

    @GetMapping("/api/inv/productos/documento/{id_documento}")
    public ResponseEntity<?> getListaProductosDocumento(@RequestHeader HttpHeaders headers, @PathVariable Long id_documento) {
        return this.productosServ.getListadoProductosDocumento(headers,id_documento);
    }

    @GetMapping("/api/inv/producto/{id_producto}")
    public ResponseEntity<?> getProducto(@RequestHeader HttpHeaders headers,
                                                @PathVariable Long id_producto) {
        return this.productosServ.getProducto(headers, id_producto);
    }

    @PostMapping(value = "/api/inv/producto")
    public ResponseEntity<?> agregarProducto(@RequestHeader HttpHeaders headers,
                                                @RequestBody Producto producto) {
        return this.productosServ.addProducto(headers,producto);
    }

    @PostMapping(value = "/api/inv/producto/item/{id_producto}/{id_documento}")
    public ResponseEntity<?> agregarItemProducto(@RequestHeader HttpHeaders headers,
                                             @RequestBody ItemProducto itemProducto,
                                             @PathVariable Long id_producto,
                                             @PathVariable Long id_documento) {
        return this.itemProductosServ.addItemProducto(headers,itemProducto,id_producto,id_documento);
    }

    @PostMapping(value = "/api/inv/producto/items/{id_documento}")
    public ResponseEntity<?> agregarItemsDocumento(@RequestHeader HttpHeaders headers,
                                                 @RequestBody List<Producto> productos,
                                                 @PathVariable Long id_documento) {
        return this.productosServ.addProductosItems(headers,productos,id_documento);
    }

    @PutMapping("/api/inv/producto/{id_producto}")
    public ResponseEntity<?> actualizarProducto(@RequestHeader HttpHeaders headers,
                                                  @RequestBody Producto producto,
                                                @PathVariable Long id_producto) {
        return this.productosServ.setProducto(headers,producto, id_producto);
    }

    @DeleteMapping("/api/inv/producto/{id_producto}")
    public ResponseEntity<?> borrarProducto(@RequestHeader HttpHeaders headers,
                                                     @PathVariable Long id_producto) {
        return this.productosServ.delProducto(headers,id_producto);
    }

    @GetMapping("/api/inv/producto/historia/{id_producto}")
    public ResponseEntity<?> getHistoriaProducto(@RequestHeader HttpHeaders headers,
                                                        @PathVariable Long id_producto) {
        return this.productosServ.getHistoriaProducto(headers,id_producto);
    }

    @PostMapping(value = "/api/inv/producto/importar", consumes = {"multipart/form-data"})
    public ResponseEntity<?> importarArchivo(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) throws IOException {
        return new ResponseEntity<>(file.getName(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/inv/producto/exportar/XML", produces = { "application/xml", "text/xml" })
    public ResponseEntity exportarXML() {
        return null;
    }

}
