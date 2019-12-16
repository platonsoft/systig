package com.systig.systigmaster.sesiones.servicios.interfaces;

import com.systig.systigmaster.sesiones.repositorios.modelos.Configuracion;
import com.systig.systigmaster.sesiones.repositorios.modelos.Propietario;
import com.systig.systigmaster.sesiones.repositorios.modelos.configuracion.FormatoDocumento;
import com.systig.systigmaster.sesiones.repositorios.modelos.configuracion.ObjConfiguracion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ISesionServ {
    ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session);
    ResponseEntity<?> addUserSystig(HttpServletRequest request, Propietario propietario);
    ResponseEntity<?> restoreUserSystig(String email);
    void enviaCorreo(String titulo, String contenido, String destinatario) throws Exception;
}
