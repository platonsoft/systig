package com.systig.systigmaster.clientes.servicios.implementados;

import com.google.gson.Gson;
import com.systig.systigmaster.clientes.modelos.Comprador;
import com.systig.systigmaster.clientes.modelos.Usuario;
import com.systig.systigmaster.clientes.repositorios.interfaces.ICompradorDao;
import com.systig.systigmaster.clientes.repositorios.interfaces.IUsuarioDao;
import com.systig.systigmaster.clientes.servicios.interfaces.ICompradorServ;
import com.systig.systigmaster.clientes.utilidades.Utilidades;
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
public class CompradorServ implements ICompradorServ {

    @Autowired
    IUsuarioDao usuarioDao;

    @Autowired
    ICompradorDao compradorDao;

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
                return new ResponseEntity<List>(this.compradorDao.findAllByUsernameEquals(usuario.getUsername()), HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getComprador(HttpHeaders headers, HttpSession session, Long idComprador) {
        try {
            Usuario usuario = Utilidades.statusSession(headers, session);
            if (usuario != null) {
                return new ResponseEntity<>(this.compradorDao.getByIdCompradorEquals(idComprador), HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> nuevoComprador(HttpHeaders headers, HttpSession session, Comprador comprador) {
        try {
            Usuario usuario = Utilidades.statusSession(headers, session);
            if (usuario != null) {
                Comprador productoResultado = this.compradorDao.save(comprador);
                return new ResponseEntity<Comprador>(productoResultado, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> actualizarComprador(HttpHeaders headers, HttpSession session, Comprador comprador) {
        try {
            Usuario usuario = Utilidades.statusSession(headers, session);
            if (usuario != null) {
                Comprador productoResultado = this.compradorDao.save(comprador);
                return new ResponseEntity<Comprador>(productoResultado, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> siguienteEtapaComprador(HttpHeaders headers, HttpSession session, Long comprador) {
        return null;
    }

    @Override
    public ResponseEntity<?> anteriorEtapaComprador(HttpHeaders headers, HttpSession session, Long comprador) {
        return null;
    }

    @Override
    public ResponseEntity<?> asignarCampanaComprador(HttpHeaders headers, HttpSession session, Long id_comprador, Long id_campana) {
        return null;
    }

    @Override
    public ResponseEntity<?> borrarComprador(HttpHeaders headers, HttpSession session, Long idComprador) {
        try {
            Usuario usuario = Utilidades.statusSession(headers, session);
            if (usuario != null) {
                this.compradorDao.deleteById(idComprador);
                return new ResponseEntity<String>("Borrado Correcto", HttpStatus.OK);
            }
            return new ResponseEntity<String>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getHistoriaComprador(HttpHeaders headers, HttpSession session, Long idComprador) {
        return null;
    }
}
