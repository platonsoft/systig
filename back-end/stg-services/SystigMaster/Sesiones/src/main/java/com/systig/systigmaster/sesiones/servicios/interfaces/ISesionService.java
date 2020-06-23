package com.systig.systigmaster.sesiones.servicios.interfaces;

import com.systig.base.repositorios.nominas.entidades.Cargo;
import com.systig.base.repositorios.nominas.entidades.Empresa;
import com.systig.base.repositorios.nominas.entidades.Persona;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface ISesionService {
    ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session);
    ResponseEntity<?> getPersona(HttpHeaders headers);
    ResponseEntity<?> addEmpresa(HttpHeaders headers, Empresa empresa, Cargo cargo);
    ResponseEntity<?> addPersona(HttpServletRequest request, String persona);
    ResponseEntity<?> restoreUserSystig(String email);
    void enviaCorreo(String titulo, String contenido, String destinatario) throws Exception;
}
