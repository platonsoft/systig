package com.systig.systigmaster.proveedores.servicios.servicios;

import com.google.gson.Gson;
import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.proveedores.entidades.Proveedor;
import com.systig.base.repositorios.proveedores.oad.IEmpresaEnvios;
import com.systig.base.repositorios.proveedores.oad.IProveedorDao;
import com.systig.base.repositorios.sesiones.entidades.Usuario;
import com.systig.base.repositorios.sesiones.oad.IUsuarioDao;
import com.systig.systigmaster.proveedores.servicios.interfaces.IProveedorServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProveedorServ implements IProveedorServ {

    private final IUsuarioDao iUsuarioDao;
    private final IProveedorDao proveedorDao;
    private final IEmpresaEnvios iEmpresaEnvios;

    public ProveedorServ(IUsuarioDao iUsuarioDao, IProveedorDao proveedorDao, IEmpresaEnvios iEmpresaEnvios) {
        this.iUsuarioDao = iUsuarioDao;
        this.proveedorDao = proveedorDao;
        this.iEmpresaEnvios = iEmpresaEnvios;
    }

    @Override
    public ResponseEntity<?> getListadoLigero(HttpHeaders headers, HttpSession session) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.proveedorDao.findAllByIdPropietarioEquals(usuario.getPropietario().getIdPropietario()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getProveedor(HttpHeaders headers, HttpSession session, Long idProveedor) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.proveedorDao.getByIdProveedor(idProveedor));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> nuevoProveedor(HttpHeaders headers, HttpSession session, Proveedor proveedor) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);

            System.out.println("Proveedor recibido --- > " + (new Gson()).toJson(proveedor));

            if(usuario!=null){
                proveedor.setIdPropietario(usuario.getPropietario().getIdPropietario());
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                proveedor.setEnvios(iEmpresaEnvios.save(proveedor.getEnvios()));
                resultadoTransaccion.setResultado(this.proveedorDao.save(proveedor));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> actualizarProveedor(HttpHeaders headers, HttpSession session, Proveedor proveedor, Long idProveedor) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                System.out.println(idProveedor + " - Proveedor recibido --- > " + (new Gson()).toJson(proveedor));
                proveedor.setIdPropietario(usuario.getPropietario().getIdPropietario());
                proveedor.setIdProveedor(idProveedor);
                proveedor.setEnvios(iEmpresaEnvios.save(proveedor.getEnvios()));
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.proveedorDao.save(proveedor));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
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
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                this.proveedorDao.deleteById(idProveedor);
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
    public ResponseEntity<?> getHistoriaProveedor(HttpHeaders headers, HttpSession session, Long idProveedor) {
        return null;
    }
}
