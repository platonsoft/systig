package com.systig.systigmaster.clientes.controladores;

import com.systig.base.repositorios.clientes.entidades.Comprador;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.systigmaster.clientes.servicios.interfaces.ICompradorServ;
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
public class CompradorCtrl {

    private final ICompradorServ compradorServ;

    public CompradorCtrl(ICompradorServ compradorServ) {
        this.compradorServ = compradorServ;
    }

    @GetMapping("/api/crm/clientes")
    public ResponseEntity<?> getListaClientes(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.compradorServ.getListadoLigero(headers, session);
    }

    @GetMapping("/api/crm/cliente/{id_cliente}")
    public ResponseEntity<?> getCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                        @PathVariable Long id_cliente) {
        return this.compradorServ.getComprador(headers, session, id_cliente);
    }

    @GetMapping("/api/crm/cliente/{campo_filtro}/{numero_identificacion}")
    public ResponseEntity<?> getCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                        @PathVariable String campo_filtro,
                                        @PathVariable String numero_identificacion) {
        return this.compradorServ.getComprador(headers, session, campo_filtro, numero_identificacion);
    }

    @PostMapping("/api/crm/cliente")
    public ResponseEntity<?> agregarCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                            @RequestBody Persona persona) {
        return this.compradorServ.nuevoComprador(headers, session, persona);
    }

    @PutMapping("/api/crm/cliente/{nro_identificacion}")
    public ResponseEntity<?> actualizarCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                               @RequestBody Persona persona,
                                               @PathVariable String nro_identificacion) {
        return this.compradorServ.actualizarComprador(headers, session, persona, nro_identificacion);
    }

    @DeleteMapping("/api/crm/cliente/{id_comprador}")
    public ResponseEntity<?> borrarCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                           @PathVariable Long id_comprador) {
        return this.compradorServ.borrarComprador(headers, session, id_comprador);
    }

    @GetMapping("/api/crm/cliente/historia/{id_comprador}")
    public ResponseEntity<?> getHistoriaCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                                @PathVariable Long id_comprador) {
        return this.compradorServ.getHistoriaComprador(headers, session, id_comprador);
    }

    @GetMapping("/api/crm/cliente/{id_comprador}/etapa/siguiente")
    public ResponseEntity<?> getSiguienteEtapa(@RequestHeader HttpHeaders headers, HttpSession session,
                                               @PathVariable Long id_comprador) {
        return this.compradorServ.siguienteEtapaComprador(headers, session, id_comprador);
    }

    @GetMapping("/api/crm/cliente/{id_comprador}/etapa/anterior")
    public ResponseEntity<?> getAnteriorEtapa(@RequestHeader HttpHeaders headers, HttpSession session,
                                              @PathVariable Long id_comprador) {
        return this.compradorServ.anteriorEtapaComprador(headers, session, id_comprador);
    }

    @GetMapping("/api/crm/cliente/{id_comprador}/campana/{id_campana}")
    public ResponseEntity<?> cambiarCampana(@RequestHeader HttpHeaders headers, HttpSession session,
                                            @PathVariable Long id_comprador, @PathVariable Long id_campana) {
        return this.compradorServ.asignarCampanaComprador(headers, session, id_comprador, id_campana);
    }

    @PostMapping(value = "/api/crm/cliente/importar", consumes = {"multipart/form-data"})
    public ResponseEntity<?> importarArchivo(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) throws IOException {
        return new ResponseEntity<>(file.getName(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/crm/cliente/exportar/XML", produces = {"application/xml", "text/xml"})
    public ResponseEntity exportarXML() {
        return null;
    }
}
