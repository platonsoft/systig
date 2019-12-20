package com.systig.systigmaster.clientes.servicios.servicios;

import com.google.gson.Gson;
import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.clientes.entidades.Comprador;
import com.systig.base.repositorios.clientes.oad.ICompradorDao;
import com.systig.base.repositorios.sesiones.entidades.Usuario;
import com.systig.base.repositorios.sesiones.oad.IUsuarioDao;
import com.systig.systigmaster.clientes.servicios.interfaces.ICompradorServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CompradorServ implements ICompradorServ {

    private final IUsuarioDao iUsuarioDao;

    private final ICompradorDao compradorDao;

    public CompradorServ(IUsuarioDao iUsuarioDao, ICompradorDao compradorDao) {
        this.iUsuarioDao = iUsuarioDao;
        this.compradorDao = compradorDao;
    }

    @Override
    public ResponseEntity<?> getListadoLigero(HttpHeaders headers, HttpSession session) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.compradorDao.findAllByIdPropietarioEquals(usuario.getPropietario().getIdPropietario()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getComprador(HttpHeaders headers, HttpSession session, Long idComprador) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.compradorDao.getOne(idComprador));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getComprador(HttpHeaders headers, HttpSession session,String campoFiltro, String numeroIdentificacion) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.compradorDao.getByNumeroIdentificacionEquals(numeroIdentificacion));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> nuevoComprador(HttpHeaders headers, HttpSession session, Comprador comprador) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);

            System.out.println("Cliente recibido --- > " + (new Gson()).toJson(comprador));

            if(usuario!=null){
                comprador.setIdPropietario(usuario.getPropietario().getIdPropietario());
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.compradorDao.save(comprador));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> actualizarComprador(HttpHeaders headers, HttpSession session, Comprador comprador, Long idComprador) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                System.out.println(idComprador + " - Comprador recibido --- > " + (new Gson()).toJson(comprador));
                comprador.setIdPropietario(usuario.getPropietario().getIdPropietario());
                comprador.setIdComprador(idComprador);
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.compradorDao.save(comprador));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Actualizacion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Actualizacion Fallida", HttpStatus.UNAUTHORIZED);
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
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                this.compradorDao.deleteById(idComprador);
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado("Borrado Correcto");
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getHistoriaComprador(HttpHeaders headers, HttpSession session, Long idComprador) {
        return null;
    }
}
