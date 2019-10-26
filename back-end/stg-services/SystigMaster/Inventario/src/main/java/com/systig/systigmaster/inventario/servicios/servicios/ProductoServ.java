package com.systig.systigmaster.inventario.servicios.servicios;

import com.google.gson.Gson;
import com.systig.systigmaster.inventario.repositorios.modelos.Producto;
import com.systig.systigmaster.inventario.repositorios.modelos.Usuario;
import com.systig.systigmaster.inventario.repositorios.interfaces.*;
import com.systig.systigmaster.inventario.servicios.interfaces.IProductosServ;
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
public class ProductoServ implements IProductosServ {

    private final IProductoDao iProductoDao;
    private final IItemProductoDao iItemProductoDao;
    private final IHistoriaDao iHistoriaDao;
    private final IUsuarioDao iUsuarioDao;

    public ProductoServ(IProductoDao iProductoDao, IItemProductoDao iItemProductoDao, IHistoriaDao iHistoriaDao, IUsuarioDao iUsuarioDao) {
        this.iProductoDao = iProductoDao;
        this.iItemProductoDao = iItemProductoDao;
        this.iHistoriaDao = iHistoriaDao;
        this.iUsuarioDao = iUsuarioDao;
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
    public ResponseEntity<?> getListadoProductos(HttpHeaders headers, HttpSession session) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                return new ResponseEntity<List>(this.iProductoDao.findAllByPropietarioIdPropietario(usuario.getPropietario().getIdPropietario()), HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getListadoProductosDocumento(HttpHeaders headers, HttpSession session, Long idDocumento) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                return new ResponseEntity<List>(
                        this.iItemProductoDao.findAllByIdDocumentoEquals(idDocumento), HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getProducto(HttpHeaders headers, HttpSession session, Long idProducto) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                return new ResponseEntity<List>(this.iProductoDao.findAllByIdProduto(idProducto), HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> addProducto(HttpHeaders headers, HttpSession session, Producto producto) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                Producto productoResultado = this.iProductoDao.save(producto);
                return new ResponseEntity<Producto>(productoResultado, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> setProducto(HttpHeaders headers, HttpSession session, Producto producto) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                Producto productoResultado = this.iProductoDao.save(producto);
                return new ResponseEntity<Producto>(productoResultado, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Actualizacion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Actualizacion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> delProducto(HttpHeaders headers, HttpSession session, Long idProducto) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                this.iProductoDao.deleteById(idProducto);
                return new ResponseEntity<String>("Borrado Correcto", HttpStatus.OK);
            }
            return new ResponseEntity<String>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getHistoriaProducto(HttpHeaders headers, HttpSession session, Long idProducto) {
        try{
            Usuario usuario = iUsuarioDao.statusSession(headers,session);
            if(usuario!=null){
                return new ResponseEntity<List>(this.iHistoriaDao.findAllByElementoEquals(String.valueOf(idProducto)), HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

}
