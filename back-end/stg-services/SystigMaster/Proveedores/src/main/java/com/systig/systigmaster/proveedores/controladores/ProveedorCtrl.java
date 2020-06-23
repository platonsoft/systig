package com.systig.systigmaster.proveedores.controladores;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.nominas.entidades.Empresa;
import com.systig.base.repositorios.proveedores.entidades.Proveedor;
import com.systig.systigmaster.proveedores.servicios.interfaces.IProveedorServ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"Proveedores"})
public class ProveedorCtrl {
    private final IProveedorServ proveedorServ;

    public ProveedorCtrl(IProveedorServ proveedorServ) {
        this.proveedorServ = proveedorServ;
    }

    @GetMapping("/api/prv/proveedores")
    @ApiOperation(value = "Muestra un listado de los proveedor de productos o servicios de un usuario")
    public ResponseEntity<ResultadoTransaccion> getListaProveedor(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.proveedorServ.getListadoLigeroProveedores(headers, session);
    }

    @GetMapping("/api/prv/clientes")
    @ApiOperation(value = "Muestra un listado de los clientes a los cuales el usuario le ofrece sus productos o servicios")
    public ResponseEntity<ResultadoTransaccion> getListaClientes(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.proveedorServ.getListadoLigeroClientes(headers, session);
    }

    @GetMapping("/api/prv/proveedor")
    @ApiOperation(value = "Muestra la informacion por id de un proveedor de productos o servicios")
    public ResponseEntity<ResultadoTransaccion> getProveedor(@RequestHeader HttpHeaders headers) {
        return this.proveedorServ.getProveedor(headers);
    }

    @PostMapping("/api/prv/proveedor")
    @ApiOperation(value = "Agrega un proveedor de productos o servicios")
    public ResponseEntity<ResultadoTransaccion> agregarProveedor(@RequestHeader HttpHeaders headers, @RequestBody Empresa proveedor) {
        return this.proveedorServ.nuevoProveedor(headers, proveedor);
    }

    @PutMapping("/api/prv/proveedor/{id_proveedor}")
    @ApiOperation(value = "Actualizar la informacion de un proveedor de productos o servicios")
    public ResponseEntity<ResultadoTransaccion> actualizarProveedor(@RequestHeader HttpHeaders headers,
                                                 @RequestBody Proveedor proveedor,
                                                 @PathVariable Long id_proveedor) {
        return this.proveedorServ.actualizarProveedor(headers, proveedor, id_proveedor);
    }

    @DeleteMapping("/api/prv/proveedor/{id_proveedor}")
    @ApiOperation(value = "Eliminar un proveedor de productos o servicios")
    public ResponseEntity<ResultadoTransaccion> borrarProveedor(@RequestHeader HttpHeaders headers, HttpSession session,
                                           @PathVariable Long id_proveedor) {
        return this.proveedorServ.borrarProveedor(headers, session, id_proveedor);
    }

    @GetMapping("/api/prv/info")
    @ApiOperation(value = "Servicio de prueba")
    public ResponseEntity<String> info() {
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

}
