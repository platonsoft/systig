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
        return this.proveedorServ.getListadoLigero(headers, session);
    }

    @GetMapping("/api/prv/proveedor/{id_proveedor}")
    public ResponseEntity<?> getProveedor(@RequestHeader HttpHeaders headers, HttpSession session,
                                        @PathVariable Long id_proveedor) {
        return this.proveedorServ.getProveedor(headers, session, id_proveedor);
    }

    @PostMapping("/api/prv/proveedor")
    public ResponseEntity<?> agregarProveedor(@RequestHeader HttpHeaders headers, HttpSession session,
                                            @RequestBody Proveedor proveedor) {
        return this.proveedorServ.nuevoProveedor(headers, session, proveedor);
    }

    @PutMapping("/api/prv/proveedor/{id_proveedor}")
    public ResponseEntity<?> actualizarProveedor(@RequestHeader HttpHeaders headers, HttpSession session,
                                                 @RequestBody Proveedor proveedor,
                                                 @PathVariable Long id_proveedor) {
        return this.proveedorServ.actualizarProveedor(headers, session, proveedor, id_proveedor);
    }

    @DeleteMapping("/api/prv/proveedor/{id_proveedor}")
    public ResponseEntity<?> borrarProveedor(@RequestHeader HttpHeaders headers, HttpSession session,
                                           @PathVariable Long id_proveedor) {
        return this.proveedorServ.borrarProveedor(headers, session, id_proveedor);
    }

    @GetMapping("/api/prv/cliente/historia/{id_proveedor}")
    public ResponseEntity<?> getHistoriaProveedor(@RequestHeader HttpHeaders headers, HttpSession session,
                                                @PathVariable Long id_proveedor) {
        return this.proveedorServ.getHistoriaProveedor(headers, session, id_proveedor);
    }

    @PostMapping(value = "/api/prv/proveedor/importar", consumes = {"multipart/form-data"})
    public ResponseEntity<?> importarArchivo(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) throws IOException {
        return new ResponseEntity<>(file.getName(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/prv/proveedor/exportar/XML", produces = {"application/xml", "text/xml"})
    public ResponseEntity exportarXML() {
        return null;
    }
}
