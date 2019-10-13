package com.systig.systigmaster.inventario.servicios.servicios;

import com.systig.systigmaster.inventario.modelos.UsuarioActivo;
import com.systig.systigmaster.inventario.repositorios.interfaces.CrudUsuarioRepo;
import com.systig.systigmaster.inventario.servicios.interfaces.IUsuarioServ;
import com.systig.systigmaster.inventario.utilidades.Utilidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Service
public class UsuarioServ implements IUsuarioServ {

    @Autowired
    CrudUsuarioRepo usuarioRepo;

    @Override
    public String getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session) {
        return Utilidades.retornoToken(this.usuarioRepo.getSession(principal, headers, session));
    }

    @Override
    public UsuarioActivo getUsuarioToken(String token) {
        return Utilidades.retornoUsuario(token);
    }
}
