package com.systig.systigmaster.sesiones.servicios.interfaces;

import com.systig.systigmaster.sesiones.repositorios.modelos.Propietario;
import com.systig.systigmaster.sesiones.repositorios.modelos.Usuario;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface ISesionServ {
    ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session);
    ResponseEntity<?> addUserSystig(HttpServletRequest request, HttpHeaders headers, HttpSession session, Propietario propietario);
    ResponseEntity<?> restoreUserSystig(String email);
    void enviaCorreo(String titulo, String contenido, String destinatario) throws Exception;
}
