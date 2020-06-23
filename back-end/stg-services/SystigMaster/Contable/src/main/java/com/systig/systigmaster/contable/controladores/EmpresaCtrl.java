package com.systig.systigmaster.contable.controladores;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.nominas.entidades.Empresa;
import com.systig.systigmaster.contable.servicios.interfaces.IEmpresaServ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"Empresas"})
public class EmpresaCtrl {


    private final  IEmpresaServ iEmpresaServ;

    public EmpresaCtrl(IEmpresaServ iEmpresaServ) {
        this.iEmpresaServ = iEmpresaServ;
    }

    @GetMapping("/api/empresas")
    @ApiOperation(value = "Listado de Empresas", notes = "Muestra la lista de las Empresas segun su propietario")
    public ResponseEntity<ResultadoTransaccion> listaEmpresas(@RequestHeader HttpHeaders headers) {
        return this.iEmpresaServ.lista(headers);
    }


    @GetMapping(value = "/api/empresa/{idEmpresa}")
    @ApiOperation(value = "Ver Empresa por id")
    public ResponseEntity<ResultadoTransaccion> getEmpresa(@RequestHeader HttpHeaders headers, @PathVariable Long idEmpresa) {
        return this.iEmpresaServ.getEmpresa(headers, idEmpresa);
    }

    @GetMapping("/api/empresa")
    @ApiOperation(value = "Ver Empresa por tipo y numero de documento")
    public ResponseEntity<ResultadoTransaccion> listaEmpresas(@RequestHeader HttpHeaders headers,
                                           @RequestParam(name = "tipoDoc") Long tipoDoc,
                                           @RequestParam(name = "nroDoc") String nroDoc) {
        return this.iEmpresaServ.getEmpresa(headers, tipoDoc, nroDoc);
    }

    @PostMapping("/api/empresa")
    @ApiOperation(value = "Agregar empresa nueva")
    public ResponseEntity<ResultadoTransaccion> agregarEmpresa(@RequestHeader HttpHeaders headers, @RequestBody Empresa empresa) {
        return this.iEmpresaServ.nuevaEmpresa(headers, empresa);
    }

    @PutMapping("/api/empresa")
    @ApiOperation(value = "Actualizar informacion de una empresa")
    public ResponseEntity<ResultadoTransaccion> actualizarEmpresa(@RequestHeader HttpHeaders headers, @RequestBody Empresa empresa) {
        return this.iEmpresaServ.actualizarEmpresa(headers, empresa);
    }

    @DeleteMapping("/api/empresa")
    @ApiOperation(value = "Eliminar una empresa")
    public ResponseEntity<ResultadoTransaccion> agregarEmpresa(@RequestHeader HttpHeaders headers, @RequestParam(name = "idEmpresa") Long idEmpresa) {
        return this.iEmpresaServ.borrarEmpresa(headers, idEmpresa);
    }

    @GetMapping("/api/empresa/info")
    @ApiOperation(value = "Servicio de prueba")
    public ResponseEntity<?> info() {
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
