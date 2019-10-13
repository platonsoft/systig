package com.systig.systigmaster.inventario.servicios.interfaces;

import com.systig.systigmaster.inventario.modelos.UsuarioActivo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface IUsuarioServ {
    String getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session);
    UsuarioActivo getUsuarioToken(String token);
}
