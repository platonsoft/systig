package com.systig.systigmaster.sesiones.controladores;

import com.systig.base.sesiones.repositorio.modelo.entidades.Propietario;
import com.systig.systigmaster.sesiones.servicios.interfaces.ISesionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.security.Principal;

@RestController
@CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token","Authorization"})
public class SesionCtrl {

    private final ISesionService sesionServ;

    public SesionCtrl(ISesionService sesionServ) {
        this.sesionServ = sesionServ;
    }

    @GetMapping("/api/login")
    public ResponseEntity<?> login(Principal principal, HttpServletRequest requests, HttpSession session) {
        return this.sesionServ.getTokenSession(principal,requests,session);
    }

    @PostMapping(value = "/api/registro")
    public ResponseEntity<?> agregarUsuario(@Context HttpServletRequest request,
                                            @RequestBody Propietario propietario) {
        System.out.println("Entramos a Agregar...");
        return this.sesionServ.addUserSystig(request,propietario);
    }

    @PostMapping(value = "/api/recuperar")
    public ResponseEntity<?> recuperar(@RequestBody String email) {
        return this.sesionServ.restoreUserSystig(email);
    }

    @GetMapping("/api/test")
    public ResponseEntity<?> test(@Context HttpServletRequest request) {
        return new ResponseEntity<>(new Propietario(), HttpStatus.OK);
    }
}
