package com.systig.systigmaster.contable.servicios.servicios;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.nominas.entidades.Empresa;
import com.systig.base.repositorios.nominas.entidades.EmpresaXPersona;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.base.repositorios.nominas.oad.IEmpresaDao;
import com.systig.base.repositorios.nominas.oad.IEmpresaXPersonaDao;
import com.systig.base.repositorios.nominas.oad.IPersonaDao;
import com.systig.systigmaster.contable.servicios.interfaces.IEmpresaServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpresaServ implements IEmpresaServ {
    private final IPersonaDao iPersonaDao;
    private final IEmpresaXPersonaDao iEmpresaXPersonaDao;
    private final IEmpresaDao iEmpresaDao;

    public EmpresaServ(IPersonaDao iPersonaDao, IEmpresaXPersonaDao iEmpresaXPersonaDao, IEmpresaDao iEmpresaDao) {
        this.iPersonaDao = iPersonaDao;
        this.iEmpresaXPersonaDao = iEmpresaXPersonaDao;
        this.iEmpresaDao = iEmpresaDao;
    }


    @Override
    public ResponseEntity<ResultadoTransaccion> lista(HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                List<Empresa> empresas = new ArrayList<>();
                iEmpresaXPersonaDao.findAll().stream()
                        .filter(persona -> persona.getIdPersona().equals(usuario.getIdPersona()))
                        .collect(Collectors.toList()).forEach(empresaXPersona -> empresas.add(empresaXPersona.getIdEmpresa()));

                resultadoTransaccion.setResultado(empresas);
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
    public ResponseEntity<ResultadoTransaccion> getEmpresa(HttpHeaders headers, Long idEmpresa) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresas = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona -> empresaXPersona.getIdEmpresa().getIdEmpresa().equals(idEmpresa))
                        .findFirst();

                if (empresas.isPresent()){
                    resultadoTransaccion.setResultado(empresas);
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
                }else{
                    resultadoTransaccion.setResultado("No se encontro la empresa");

                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.BAD_REQUEST);
                }
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
    public ResponseEntity<ResultadoTransaccion> getEmpresa(HttpHeaders headers, Long tipoDoc, String nroDoc) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresas = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona -> empresaXPersona.getIdEmpresa().getNroIdentificacion().equals(nroDoc))
                        .filter(empresaXPersona -> empresaXPersona.getIdEmpresa().getTipoIdentificacion().getIdTipoDocIdentif().equals(tipoDoc))
                        .findFirst();

                if (empresas.isPresent()){
                    resultadoTransaccion.setResultado(empresas);
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
                }else{
                    resultadoTransaccion.setResultado("No se encontro la empresa");
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.BAD_REQUEST);
                }
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
    public ResponseEntity<ResultadoTransaccion> nuevaEmpresa(HttpHeaders headers, Empresa empresa) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresas = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona -> empresaXPersona.getIdEmpresa().getNroIdentificacion().equals(empresa.getNroIdentificacion()))
                        .filter(empresaXPersona -> empresaXPersona.getIdEmpresa().getTipoIdentificacion().getIdTipoDocIdentif().equals(empresa.getTipoIdentificacion().getIdTipoDocIdentif()))
                        .findFirst();

                if (!empresas.isPresent()){
                    resultadoTransaccion.setResultado(iEmpresaDao.save(empresa));
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
                }else{
                    resultadoTransaccion.setResultado("La empresa ya esta registrada");
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.BAD_REQUEST);
                }
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
    public ResponseEntity<ResultadoTransaccion> actualizarEmpresa(HttpHeaders headers, Empresa empresa) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresas = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona -> empresaXPersona.getIdEmpresa().getNroIdentificacion().equals(empresa.getNroIdentificacion()))
                        .filter(empresaXPersona -> empresaXPersona.getIdEmpresa().getTipoIdentificacion().getIdTipoDocIdentif().equals(empresa.getTipoIdentificacion().getIdTipoDocIdentif()))
                        .findFirst();

                if (empresas.isPresent()){
                    resultadoTransaccion.setResultado(iEmpresaDao.save(empresa));
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
                }else{
                    resultadoTransaccion.setResultado("La empresa ya esta registrada");
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.BAD_REQUEST);
                }
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
    public ResponseEntity<ResultadoTransaccion> borrarEmpresa(HttpHeaders headers, Long idEmpresa) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{

            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));

                Optional<EmpresaXPersona> empresas = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona -> empresaXPersona.getIdEmpresa().getIdEmpresa().equals(idEmpresa))
                        .findFirst();

                if (!empresas.isPresent()){
                    Empresa empresaA = empresas.get().getIdEmpresa();
                    empresaA.setIdEmpresa(null);
                    iEmpresaDao.save(empresaA);
                    iEmpresaXPersonaDao.delete(empresas.get());
                    resultadoTransaccion.setResultado("Se ha borrado correctamente");
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
                }else{
                    resultadoTransaccion.setResultado("No se encontro la empresa");
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.BAD_REQUEST);
                }
            }
            resultadoTransaccion.setResultado("Acceso denegado");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
