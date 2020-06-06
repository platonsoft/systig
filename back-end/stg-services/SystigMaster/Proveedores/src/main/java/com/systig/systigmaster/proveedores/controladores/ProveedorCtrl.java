package com.systig.systigmaster.proveedores.controladores;

import com.systig.base.repositorios.proveedores.entidades.Proveedor;
import com.systig.systigmaster.proveedores.servicios.interfaces.IProveedorServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token","Authorization"})
public class ProveedorCtrl {
    private final IProveedorServ proveedorServ;

    public ProveedorCtrl(IProveedorServ proveedorServ) {
        this.proveedorServ = proveedorServ;
    }

    @GetMapping("/api/prv/proveedores")
    public ResponseEntity<?> getListaProveedor(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.proveedorServ.getListadoLigeroProveedores(headers, session);
    }

    @GetMapping("/api/prv/clientes")
    public ResponseEntity<?> getListaClientes(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.proveedorServ.getListadoLigeroClientes(headers, session);
    }

    @GetMapping("/api/prv/proveedor")
    public ResponseEntity<?> getProveedor(@RequestHeader HttpHeaders headers) {
        return this.proveedorServ.getProveedor(headers);
    }

    @PostMapping("/api/prv/proveedor/{tipoDocAbrev}/{nroDoc}")
    public ResponseEntity<?> agregarProveedor(@RequestHeader HttpHeaders headers, HttpSession session,
                                            @PathVariable String tipoDocAbrev, @PathVariable String nroDoc) {
        return this.proveedorServ.nuevoProveedor(headers, tipoDocAbrev, nroDoc);
    }

    @PutMapping("/api/prv/proveedor/{id_proveedor}")
    public ResponseEntity<?> actualizarProveedor(@RequestHeader HttpHeaders headers,
                                                 @RequestBody Proveedor proveedor,
                                                 @PathVariable Long id_proveedor) {
        return this.proveedorServ.actualizarProveedor(headers, proveedor, id_proveedor);
    }

    @DeleteMapping("/api/prv/proveedor/{id_proveedor}")
    public ResponseEntity<?> borrarProveedor(@RequestHeader HttpHeaders headers, HttpSession session,
                                           @PathVariable Long id_proveedor) {
        return this.proveedorServ.borrarProveedor(headers, session, id_proveedor);
    }

}
