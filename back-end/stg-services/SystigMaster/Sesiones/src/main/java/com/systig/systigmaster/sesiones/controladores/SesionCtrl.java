package com.systig.systigmaster.sesiones.controladores;

import com.google.gson.Gson;
import com.systig.base.repositorios.nominas.entidades.Cargo;
import com.systig.base.repositorios.nominas.entidades.Empresa;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.systigmaster.sesiones.servicios.interfaces.ISesionService;
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
@CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token","Authorization"})
public class SesionCtrl {

    private final ISesionService sesionServ;

    public SesionCtrl(ISesionService sesionServ) {
        this.sesionServ = sesionServ;
    }

    @GetMapping("/api/login")
    public ResponseEntity<?> login(Principal principal, HttpServletRequest requests, HttpSession session) {
        System.out.println("Usuario: " + principal.getName());
        return this.sesionServ.getTokenSession(principal,requests,session);
    }

    @PostMapping(value = "/api/registro/persona")
    public ResponseEntity<?> agregarUsuario(@Context HttpServletRequest request,
                                            @RequestBody String propietario) {
        Persona prop = (new Gson()).fromJson(propietario, Persona.class);
        return this.sesionServ.addPersona(request,prop);
    }

    @PostMapping(value = "/api/registro/empresa")
    public ResponseEntity<?> ageregarEmpresa(@RequestHeader HttpHeaders headers,
                                            @RequestBody String empresaycargo) {
        Empresa empresa = new Empresa();
        Cargo cargo = new Cargo();

        Map<String, Object> prop = (new Gson()).fromJson(empresaycargo, Map.class);

        for (Map.Entry<String, Object> entry : prop.entrySet()) {
            if (entry.getValue() instanceof Empresa) {
                empresa = (Empresa) entry.getValue();
            } else if (entry.getValue() instanceof Cargo) {
                cargo = (Cargo)entry.getValue();
            } else {
                throw new IllegalStateException("Expecting either String or Class as entry value");
            }
        }
        return this.sesionServ.addEmpresa(headers, empresa, cargo);
    }

    @PostMapping(value = "/api/recuperar")
    public ResponseEntity<?> recuperar(@RequestBody String email) {
        return this.sesionServ.restoreUserSystig(email);
    }

    @GetMapping("/api/sesion/configuracion")
    public ResponseEntity<?> getConfiguracion(@RequestHeader HttpHeaders headers) {
        return this.sesionServ.getPersona(headers);
    }

    @GetMapping("/api/test")
    public ResponseEntity<?> test(@Context HttpServletRequest request) {
        return new ResponseEntity<>(new Empresa(), HttpStatus.OK);
    }
}
