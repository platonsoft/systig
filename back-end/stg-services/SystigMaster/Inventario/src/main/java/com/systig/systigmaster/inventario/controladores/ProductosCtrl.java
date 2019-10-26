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
public class ProductosCtrl {

    private final IProductosServ productosServ;

    public ProductosCtrl(IProductosServ productosServ) {
        this.productosServ = productosServ;
    }

    @GetMapping("/api/login")
    public ResponseEntity<?> login(Principal principal, HttpServletRequest requests,HttpSession session) {
        return this.productosServ.getTokenSession(principal,requests,session);
    }

    @GetMapping("/api/inv/productos")
    public ResponseEntity<?> getListaProductos(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.productosServ.getListadoProductos(headers,session);
    }

    @GetMapping("/api/inv/productos/documento/{id_documento}")
    public ResponseEntity<?> getListaProductosDocumento(@RequestHeader HttpHeaders headers, HttpSession session, @PathVariable Long id_documento) {
        return this.productosServ.getListadoProductosDocumento(headers,session,id_documento);
    }

    @GetMapping("/api/inv/producto/{id_producto}")
    public ResponseEntity<?> getProducto(@RequestHeader HttpHeaders headers, HttpSession session,
                                                @PathVariable Long id_producto) {
        return this.productosServ.getProducto(headers, session, id_producto);
    }

    @PostMapping("/api/inv/producto")
    public ResponseEntity<?> agregarProducto(@RequestHeader HttpHeaders headers, HttpSession session,
                                                @RequestBody Producto producto) {
        return this.productosServ.addProducto(headers,session,producto);
    }

    @PutMapping("/api/inv/producto")
    public ResponseEntity<?> actualizarProducto(@RequestHeader HttpHeaders headers, HttpSession session,
                                                  @RequestBody Producto producto) {
        return this.productosServ.setProducto(headers,session,producto);
    }

    @DeleteMapping("/api/inv/producto/{id_producto}")
    public ResponseEntity<?> borrarProducto(@RequestHeader HttpHeaders headers, HttpSession session,
                                                     @PathVariable Long id_producto) {
        return this.productosServ.delProducto(headers,session,id_producto);
    }

    @GetMapping("/api/inv/producto/historia/{id_producto}")
    public ResponseEntity<?> getHistoriaProducto(@RequestHeader HttpHeaders headers, HttpSession session,
                                                        @PathVariable Long id_producto) {
        return this.productosServ.getHistoriaProducto(headers,session,id_producto);
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
