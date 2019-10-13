package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.modelos.User;
import com.systig.systigmaster.inventario.modelos.UsuarioActivo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface CrudUsuarioRepo {
    UsuarioActivo getSession(Principal principal, HttpServletRequest headers, HttpSession session);
}
