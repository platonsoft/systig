package com.systig.systigmaster.clientes.servicios.servicios;

import com.google.gson.Gson;
import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.clientes.entidades.Comprador;
import com.systig.base.repositorios.clientes.entidades.Etapa;
import com.systig.base.repositorios.clientes.oad.ICompradorDao;
import com.systig.base.repositorios.clientes.oad.IEtapaDao;
import com.systig.base.repositorios.nominas.entidades.EmpresaXPersona;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.base.repositorios.nominas.oad.IEmpresaXPersonaDao;
import com.systig.base.repositorios.nominas.oad.IPersonaDao;
import com.systig.systigmaster.clientes.servicios.interfaces.ICompradorServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class CompradorServ implements ICompradorServ {

    private final IPersonaDao iPersonaDao;
    private final IEmpresaXPersonaDao iEmpresaXPersonaDao;

    private final ICompradorDao compradorDao;
    private final IEtapaDao iEtapaDao;

    public CompradorServ(IPersonaDao iPersonaDao, IEmpresaXPersonaDao iEmpresaXPersonaDao, ICompradorDao compradorDao, IEtapaDao iEtapaDao) {
        this.iPersonaDao = iPersonaDao;
        this.iEmpresaXPersonaDao = iEmpresaXPersonaDao;
        this.compradorDao = compradorDao;
        this.iEtapaDao = iEtapaDao;
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> getListadoLigero(HttpHeaders headers, HttpSession session) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(this.compradorDao.findAllByIdEmpresa_IdEmpresa(empresaXPersona.get().getIdEmpresa().getIdEmpresa()));
                }else {
                    resultadoTransaccion.setResultado("El Usuario no tiene empresa asociada, Primero registre una antes de continuar");
                }
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);

            }
            resultadoTransaccion.setResultado("Acceso denegado");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> getComprador(HttpHeaders headers, HttpSession session, Long idComprador) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.compradorDao.getOne(idComprador));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            resultadoTransaccion.setResultado("Acceso denegado");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> getComprador(HttpHeaders headers, HttpSession session,String campoFiltro, String numeroIdentificacion) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.compradorDao.getByIdPersona_NroIdentificacion(numeroIdentificacion));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            resultadoTransaccion.setResultado("Acceso denegado");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> nuevoComprador(HttpHeaders headers, HttpSession session, Persona clientePersona) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Comprador comprador = new Comprador();
            Persona usuario = iPersonaDao.statusSession(headers);

            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                comprador.setEtapa(iEtapaDao.getFirstByNombreEquals("CANDIDATO"));

                Persona cliente = iPersonaDao.getFirstByNroIdentificacionEquals(clientePersona.getNroIdentificacion());

                if (cliente==null){
                    clientePersona.setRol(2L);
                    cliente =iPersonaDao.save(clientePersona);
                }

                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();
                if (empresaXPersona.isPresent()){
                    comprador.setIdEmpresa(empresaXPersona.get().getIdEmpresa());
                    comprador.setIdPersona(cliente);
                    comprador.setIsCliente(false);
                    resultadoTransaccion.setResultado(this.compradorDao.save(comprador));
                }else {
                    resultadoTransaccion.setResultado("El Usuario no tiene empresa asociada, Primero registre una antes de continuar");
                }
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            resultadoTransaccion.setResultado("Insersion Fallida");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> actualizarComprador(HttpHeaders headers, HttpSession session, Persona clientePersona, String nroIdentificacion) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<Persona> cliente = iPersonaDao.findAll().stream()
                        .filter(persona -> persona.getNroIdentificacion().equals(nroIdentificacion))
                        .filter(persona -> persona.getRol().equals(2L))
                        .findFirst();
                if (cliente.isPresent()){
                    Persona persona = cliente.get();
                    persona.setProvincia(clientePersona.getProvincia());
                    persona.setCodigoPostal(clientePersona.getCodigoPostal());
                    persona.setNombres(clientePersona.getNombres());
                    persona.setApellidos(clientePersona.getApellidos());
                    persona.setDireccion(clientePersona.getDireccion());
                    resultadoTransaccion.setResultado(iPersonaDao.save(persona));
                }else {
                    resultadoTransaccion.setResultado("La persona no existe o fue registrado como usuario y no como cliente, intente agregarlo como nuevo o pida al cliente que acceda al portal de systig y haga los cambios desde alli");
                }

                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            resultadoTransaccion.setResultado("Actualizacion Fallida");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> siguienteEtapaComprador(HttpHeaders headers, HttpSession session, Long comprador) {
        return null;
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> anteriorEtapaComprador(HttpHeaders headers, HttpSession session, Long comprador) {
        return null;
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> asignarCampanaComprador(HttpHeaders headers, HttpSession session, Long id_comprador, Long id_campana) {
        return null;
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> borrarComprador(HttpHeaders headers, HttpSession session, Long idComprador) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                this.compradorDao.deleteById(idComprador);
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado("Borrado Correcto");
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            resultadoTransaccion.setResultado("Borrado Fallido");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> getHistoriaComprador(HttpHeaders headers, HttpSession session, Long idComprador) {
        return null;
    }
}
