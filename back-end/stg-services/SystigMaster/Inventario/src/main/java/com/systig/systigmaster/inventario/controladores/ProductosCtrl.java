package com.systig.systigmaster.inventario.controladores;

import com.systig.systigmaster.inventario.repositorios.modelos.Producto;
import com.systig.systigmaster.inventario.servicios.interfaces.IProductosServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.Principal;


@RestController
@CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token","Authorization"})
public class ProductosCtrl {

    private final IProductosServ productosServ;

    public ProductosCtrl(IProductosServ productosServ) {
        this.productosServ = productosServ;
    }

    @GetMapping("/api/inv/productos")
    public ResponseEntity<?> getListaProductos(@RequestHeader HttpHeaders headers) {
        return this.productosServ.getListadoProductos(headers);
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
