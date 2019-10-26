package com.systig.systigmaster.contable.servicios.servicios;

import com.google.gson.Gson;
import com.systig.systigmaster.contable.repositorios.interfaces.IDocumentoDao;
import com.systig.systigmaster.contable.repositorios.interfaces.IHistoriaDao;
import com.systig.systigmaster.contable.repositorios.interfaces.IUsuarioDao;
import com.systig.systigmaster.contable.repositorios.modelos.Documento;
import com.systig.systigmaster.contable.repositorios.modelos.Usuario;
import com.systig.systigmaster.contable.servicios.interfaces.IDocumentosServ;
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
public class DocumentoServ implements IDocumentosServ {

    private final IUsuarioDao iUsuarioDao;
    private final IDocumentoDao iDocumentoDao;
    private final IHistoriaDao iHistoriaDao;

    public DocumentoServ(IUsuarioDao iUsuarioDao, IDocumentoDao iDocumentoDao, IHistoriaDao iHistoriaDao) {
        this.iUsuarioDao = iUsuarioDao;
        this.iDocumentoDao = iDocumentoDao;
        this.iHistoriaDao = iHistoriaDao;
    }

    @Override
    public ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session) {
        if (principal!=null){
            Usuario usuario = iUsuarioDao.getByUsernameEquals(principal.getName());
            usuario.setSessionId(session.getId());
            String tokenEnc = iUsuarioDao.retornoToken(usuario);
            Usuario tokenDesc = iUsuarioDao.retornoUsuario(tokenEnc);

            System.out.println("Token --> " + tokenEnc);
            System.out.println("Usuario --> " + (new Gson()).toJson(tokenDesc));
            return new ResponseEntity<String>(tokenEnc, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<String>("Acceso denegado", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<?> getListaDocumentos(HttpHeaders headers, HttpSession session, TIPO_DOCUMENTO tipoDocumento) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                return new ResponseEntity<List>(this.iDocumentoDao
                        .findAllByTipoDocumentoEqualsAndIdPropietarioEquals(
                                tipoDocumento.getTipoDocumento(),
                                usuario.getPropietario().getIdPropietario()),
                        HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getDocumento(HttpHeaders headers, HttpSession session, Long idDocumento) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                return new ResponseEntity<Documento>(this.iDocumentoDao.findByIdDocumentoEquals(idDocumento), HttpStatus.OK);
            }
            return new ResponseEntity<Documento>(new Documento(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Documento>(new Documento(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> addDocumento(HttpHeaders headers, HttpSession session, Documento documento) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                Documento productoResultado = this.iDocumentoDao.save(documento);
                return new ResponseEntity<Documento>(productoResultado, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> setDocumento(HttpHeaders headers, HttpSession session, Documento documento) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                Documento productoResultado = this.iDocumentoDao.save(documento);
                return new ResponseEntity<Documento>(productoResultado, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> chgDocumento(HttpHeaders headers, HttpSession session, Documento documento) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                // Validar antes de cambiar estado del focumento y segun el tipo
                Documento productoResultado = this.iDocumentoDao.save(documento);
                return new ResponseEntity<Documento>(productoResultado, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getHistoriaDocumentos(HttpHeaders headers, HttpSession session, Long idDocumento) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                return new ResponseEntity<List>(this.iHistoriaDao.findAllByElementoEquals(String.valueOf(idDocumento)), HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }
}
