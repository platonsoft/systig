package com.systig.systigmaster.sesiones.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.systig.systigmaster.sesiones.repositorios.modelos.GeoIP;
import com.systig.systigmaster.sesiones.repositorios.modelos.Propietario;
import com.systig.systigmaster.sesiones.repositorios.modelos.Usuario;
import com.systig.systigmaster.sesiones.servicios.interfaces.ISesionServ;
import com.systig.systigmaster.sesiones.servicios.servicios.GeoIPLocationServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.security.Principal;
import java.util.Map;

@RestController
public class SesionCtrl {

    private final ISesionServ sesionServ;

    public SesionCtrl(ISesionServ sesionServ) {
        this.sesionServ = sesionServ;
    }

    @GetMapping("/api/login")
    public ResponseEntity<?> login(Principal principal, HttpServletRequest requests, HttpSession session) {
        return this.sesionServ.getTokenSession(principal,requests,session);
    }

    @PostMapping(value = "/api/registro")
    public ResponseEntity<?> agregarUsuario(@Context HttpServletRequest request,
                                            @RequestHeader HttpHeaders headers,
                                            HttpSession session,
                                            @RequestBody Propietario propietario) {
        return this.sesionServ.addUserSystig(request, headers,session,propietario);
    }

    @PostMapping(value = "/api/recuperar")
    public ResponseEntity<?> recuperar(@RequestBody String email) {
        return this.sesionServ.restoreUserSystig(email);
    }

    @GetMapping("/api/test")
    public ResponseEntity<?> test(@Context HttpServletRequest request) throws Exception {
        return new ResponseEntity<>(new Propietario(), HttpStatus.BAD_REQUEST);
    }
}
