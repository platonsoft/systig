package com.systig.systigmaster.proveedores.servicios.implementados;

import com.google.gson.Gson;
import com.systig.systigmaster.proveedores.repositorios.interfaces.IProveedorDao;
import com.systig.systigmaster.proveedores.repositorios.interfaces.IUsuarioDao;
import com.systig.systigmaster.proveedores.repositorios.modelos.Proveedor;
import com.systig.systigmaster.proveedores.repositorios.modelos.Usuario;
import com.systig.systigmaster.proveedores.servicios.interfaces.IProveedorServ;
import com.systig.systigmaster.proveedores.utilidades.Utilidades;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorServ implements IProveedorServ {

    @Autowired
    IUsuarioDao usuarioDao;

    @Autowired
    IProveedorDao proveedorDao;

    @Override
    public ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session) {
        if (principal != null) {
            Usuario usuario = usuarioDao.getByUsernameEquals(principal.getName());
            usuario.setSessionId(session.getId());
            String tokenEnc = Utilidades.retornoToken(usuario);
            Usuario tokenDesc = Utilidades.retornoUsuario(tokenEnc);

            System.out.println("Token --> " + tokenEnc);
            System.out.println("Usuario --> " + (new Gson()).toJson(tokenDesc));
            return new ResponseEntity<String>(tokenEnc, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<String>("Acceso denegado", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> getListadoLigero(HttpHeaders headers, HttpSession session) {
        try {
            Usuario usuario = Utilidades.statusSession(headers, session);
            if (usuario != null) {
                return new ResponseEntity<List>(this.proveedorDao.findAllByIdPropietarioEquals(usuario.getPropietario().getIdPropietario()), HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getProveedor(HttpHeaders headers, HttpSession session, Long idProveedor) {
        try {
            Usuario usuario = Utilidades.statusSession(headers, session);
            if (usuario != null) {
                return new ResponseEntity<>(this.proveedorDao.getByIdProveedor(idProveedor), HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> nuevoProveedor(HttpHeaders headers, HttpSession session, Proveedor proveedor) {
        try {
            Usuario usuario = Utilidades.statusSession(headers, session);
            if (usuario != null) {
                Proveedor proveedorResultado = this.proveedorDao.save(proveedor);
                return new ResponseEntity<Proveedor>(proveedorResultado, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> actualizarProveedor(HttpHeaders headers, HttpSession session, Proveedor proveedor) {
        try {
            Usuario usuario = Utilidades.statusSession(headers, session);
            if (usuario != null) {
                Proveedor proveedorResultado = this.proveedorDao.save(proveedor);
                return new ResponseEntity<Proveedor>(proveedorResultado, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> borrarProveedor(HttpHeaders headers, HttpSession session, Long idComprador) {
        try {
            Usuario usuario = Utilidades.statusSession(headers, session);
            if (usuario != null) {
                this.proveedorDao.deleteById(idComprador);
                return new ResponseEntity<String>("Borrado Correcto", HttpStatus.OK);
            }
            return new ResponseEntity<String>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getHistoriaProveedor(HttpHeaders headers, HttpSession session, Long idComprador) {
        return null;
    }
}
