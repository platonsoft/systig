package com.systig.systigmaster.sesiones.servicios.interfaces;

import com.systig.base.sesiones.repositorio.modelo.entidades.Propietario;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface ISesionService {
    ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session);
    ResponseEntity<?> addUserSystig(HttpServletRequest request, Propietario propietario);
    ResponseEntity<?> restoreUserSystig(String email);
    void enviaCorreo(String titulo, String contenido, String destinatario) throws Exception;
}
