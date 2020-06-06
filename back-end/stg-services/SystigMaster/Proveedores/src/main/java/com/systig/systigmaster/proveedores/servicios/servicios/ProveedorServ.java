package com.systig.systigmaster.proveedores.servicios.servicios;

import com.google.gson.Gson;
import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.nominas.entidades.Empresa;
import com.systig.base.repositorios.nominas.entidades.EmpresaXPersona;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.base.repositorios.nominas.oad.IEmpresaDao;
import com.systig.base.repositorios.nominas.oad.IEmpresaXPersonaDao;
import com.systig.base.repositorios.nominas.oad.IPersonaDao;
import com.systig.base.repositorios.proveedores.entidades.Proveedor;
import com.systig.base.repositorios.proveedores.oad.IProveedorDao;
import com.systig.systigmaster.proveedores.servicios.interfaces.IProveedorServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class ProveedorServ implements IProveedorServ {

    private final IPersonaDao iPersonaDao;
    private final IEmpresaDao iEmpresaDao;
    private final IEmpresaXPersonaDao iEmpresaXPersonaDao;
    private final IProveedorDao iProveedorDao;

    public ProveedorServ(IPersonaDao iPersonaDao, IEmpresaDao iEmpresaDao, IEmpresaXPersonaDao iEmpresaXPersonaDao, IProveedorDao iProveedorDao) {
        this.iPersonaDao = iPersonaDao;
        this.iEmpresaDao = iEmpresaDao;
        this.iEmpresaXPersonaDao = iEmpresaXPersonaDao;
        this.iProveedorDao = iProveedorDao;
    }

    @Override
    public ResponseEntity<?> getListadoLigeroClientes(HttpHeaders headers, HttpSession session) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(this.iProveedorDao.findAllByCliente_IdEmpresa(empresaXPersona.get().getIdEmpresa().getIdEmpresa()));
                }else{
                    resultadoTransaccion.setResultado("El Usuario no tiene empresa asociada, Primero registre una antes de continuar");
                }
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }else{
                resultadoTransaccion.setResultado("Acceso denegado");
                resultadoTransaccion.setToken(null);
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error Interno, consulte con el administrador del sistema", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getListadoLigeroProveedores(HttpHeaders headers, HttpSession session) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(this.iProveedorDao.findAllByEmpresa_IdEmpresa(empresaXPersona.get().getIdEmpresa().getIdEmpresa()));
                }else{
                    resultadoTransaccion.setResultado("El Usuario no tiene empresa asociada, Primero registre una antes de continuar");
                }
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }else{
                resultadoTransaccion.setResultado("Acceso denegado");
                resultadoTransaccion.setToken(null);
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error Interno, consulte con el administrador del sistema", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getProveedor(HttpHeaders headers) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(this.iProveedorDao.getFirstByEmpresa_NroIdentificacionAndEmpresa_TipoIdentificacionAbrevDoc(empresaXPersona.get().getIdEmpresa().getNroIdentificacion(),empresaXPersona.get().getIdEmpresa().getTipoIdentificacion().getAbrevDoc()));
                }else{
                    resultadoTransaccion.setResultado("El Usuario no tiene empresa asociada, Primero registre una antes de continuar");
                }
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }else{
                resultadoTransaccion.setResultado("Acceso denegado");
                resultadoTransaccion.setToken(null);
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error Interno, consulte con el administrador del sistema", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getCliente(HttpHeaders headers, String tipoIdentificaionAbrev, String nroIdentificacion) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(this.iProveedorDao.getFirstByCliente_NroIdentificacionAndCliente_TipoIdentificacionAbrevDocAndCliente_IdEmpresa(nroIdentificacion,tipoIdentificaionAbrev,empresaXPersona.get().getIdEmpresa().getIdEmpresa()));
                }else{
                    resultadoTransaccion.setResultado("El Usuario no tiene empresa asociada, Primero registre una antes de continuar");
                }
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }else{
                resultadoTransaccion.setResultado("Acceso denegado");
                resultadoTransaccion.setToken(null);
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error Interno, consulte con el administrador del sistema", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> nuevoProveedor(HttpHeaders headers, String tipoIdentificaionAbrev, String nroIdentificacion) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    Empresa prov = iEmpresaDao.getFirstByTipoIdentificacion_AbrevDocAndNroIdentificacion(tipoIdentificaionAbrev,nroIdentificacion);
                    if (prov!=null){
                        Proveedor nuevoProv = new Proveedor();

                        nuevoProv.setCliente(empresaXPersona.get().getIdEmpresa());
                        nuevoProv.setEmpresa(prov);
                        resultadoTransaccion.setResultado(iProveedorDao.save(nuevoProv));
                    }else{
                        resultadoTransaccion.setResultado("El proveedor no existe en la base de datos");
                    }
                }else{
                    resultadoTransaccion.setResultado("El Usuario no tiene empresa asociada, Primero registre una antes de continuar");
                }
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }else{
                resultadoTransaccion.setResultado("Acceso denegado");
                resultadoTransaccion.setToken(null);
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error Interno, consulte con el administrador del sistema", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> actualizarProveedor(HttpHeaders headers, Proveedor proveedor, Long idProveedor) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));

                Proveedor prov = iProveedorDao.getOne(idProveedor);
                prov.setObservaciones(proveedor.getObservaciones());
                prov.setPrecioPeso(proveedor.getPrecioPeso());
                prov.setPrecioEmpaque(proveedor.getPrecioEmpaque());

                resultadoTransaccion.setResultado(this.iProveedorDao.save(prov));

                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Actualizacion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Actualizacion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> borrarProveedor(HttpHeaders headers, HttpSession session, Long idProveedor) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    Optional<Proveedor> proveedor = iProveedorDao.findAll().stream()
                            .filter(proveedor1 -> proveedor1.getCliente().getIdEmpresa().equals(empresaXPersona.get().getIdEmpresa().getIdEmpresa()))
                            .filter(proveedor1 -> proveedor1.getEmpresa().getIdEmpresa().equals(idProveedor))
                            .findFirst();
                    if (proveedor.isPresent()){
                        iProveedorDao.delete(proveedor.get());
                        resultadoTransaccion.setResultado("Borrado Correcto");
                    }else{
                        resultadoTransaccion.setResultado("El Usuario no tiene empresa asociada, Primero registre una antes de continuar");
                    }
                }
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getHistoriaProveedor(HttpHeaders headers, HttpSession session, Long idProveedor) {
        return null;
    }
}
