package com.systig.systigmaster.sesiones.servicios.interfaces;

import com.systig.base.repositorios.sesiones.entidades.Propietario;
import com.systig.base.repositorios.sesiones.entidades.Usuario;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface ISesionService {
    ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session);
    ResponseEntity<?> getUsuario(HttpHeaders headers);
    ResponseEntity<?> getListaUsuarios(HttpHeaders headers);
    ResponseEntity<?> addUsuarioPropietario(HttpHeaders headers, Usuario usuario);
    ResponseEntity<?> setPropietario(HttpHeaders headers, Propietario propietario);
    ResponseEntity<?> addUserSystig(HttpServletRequest request, Propietario propietario);
    ResponseEntity<?> restoreUserSystig(String email);
    void enviaCorreo(String titulo, String contenido, String destinatario) throws Exception;
}
