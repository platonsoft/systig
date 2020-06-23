package com.systig.systigmaster.clientes.controladores;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.clientes.entidades.Comprador;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.systigmaster.clientes.servicios.interfaces.ICompradorServ;
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
@Api(tags = {"Cliente"})
public class CompradorCtrl {

    private final ICompradorServ compradorServ;

    public CompradorCtrl(ICompradorServ compradorServ) {
        this.compradorServ = compradorServ;
    }

    @GetMapping("/api/crm/clientes")
    @ApiOperation(value = "Muestra la lista de los clientes de un usuario")
    public ResponseEntity<ResultadoTransaccion> getListaClientes(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.compradorServ.getListadoLigero(headers, session);
    }

    @GetMapping("/api/crm/cliente/{id_cliente}")
    @ApiOperation(value = "Recupera la informacion de un cliente del usuario")
    public ResponseEntity<ResultadoTransaccion> getCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                        @PathVariable Long id_cliente) {
        return this.compradorServ.getComprador(headers, session, id_cliente);
    }

    @GetMapping("/api/crm/cliente/{campo_filtro}/{numero_identificacion}")
    @ApiOperation(value = "Recupera la informacion de un cliente por su numero de documento")
    public ResponseEntity<ResultadoTransaccion> getCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                        @PathVariable String campo_filtro,
                                        @PathVariable String numero_identificacion) {
        return this.compradorServ.getComprador(headers, session, campo_filtro, numero_identificacion);
    }

    @PostMapping("/api/crm/cliente")
    @ApiOperation(value = "Agrega un cliente nuevo")
    public ResponseEntity<ResultadoTransaccion> agregarCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                            @RequestBody Persona persona) {
        return this.compradorServ.nuevoComprador(headers, session, persona);
    }

    @PutMapping("/api/crm/cliente/{nro_identificacion}")
    @ApiOperation(value = "Actualiza la informacion personal de un cliente")
    public ResponseEntity<ResultadoTransaccion> actualizarCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                               @RequestBody Persona persona,
                                               @PathVariable String nro_identificacion) {
        return this.compradorServ.actualizarComprador(headers, session, persona, nro_identificacion);
    }

    @DeleteMapping("/api/crm/cliente/{id_comprador}")
    @ApiOperation(value = "Marca como borrado a un cliente")
    public ResponseEntity<ResultadoTransaccion> borrarCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                           @PathVariable Long id_comprador) {
        return this.compradorServ.borrarComprador(headers, session, id_comprador);
    }

    @GetMapping("/api/crm/cliente/historia/{id_comprador}")
    @ApiOperation(value = "Muestra el historial de operaciones de un cliente")
    public ResponseEntity<ResultadoTransaccion> getHistoriaCliente(@RequestHeader HttpHeaders headers, HttpSession session,
                                                @PathVariable Long id_comprador) {
        return this.compradorServ.getHistoriaComprador(headers, session, id_comprador);
    }

    @GetMapping("/api/crm/cliente/{id_comprador}/etapa/siguiente")
    @ApiOperation(value = "Pasa a la siguiente etapa en una campaña de captacion de clientes")
    public ResponseEntity<ResultadoTransaccion> getSiguienteEtapa(@RequestHeader HttpHeaders headers, HttpSession session,
                                               @PathVariable Long id_comprador) {
        return this.compradorServ.siguienteEtapaComprador(headers, session, id_comprador);
    }

    @GetMapping("/api/crm/cliente/{id_comprador}/etapa/anterior")
    @ApiOperation(value = "Va a la etapa anterior en una campaña de captacion de clientes")
    public ResponseEntity<ResultadoTransaccion> getAnteriorEtapa(@RequestHeader HttpHeaders headers, HttpSession session,
                                              @PathVariable Long id_comprador) {
        return this.compradorServ.anteriorEtapaComprador(headers, session, id_comprador);
    }

    @GetMapping("/api/crm/cliente/{id_comprador}/campana/{id_campana}")
    @ApiOperation(value = "Cambia la campaña a la cual esta asociado el cliente")
    public ResponseEntity<ResultadoTransaccion> cambiarCampana(@RequestHeader HttpHeaders headers, HttpSession session,
                                            @PathVariable Long id_comprador, @PathVariable Long id_campana) {
        return this.compradorServ.asignarCampanaComprador(headers, session, id_comprador, id_campana);
    }
}
