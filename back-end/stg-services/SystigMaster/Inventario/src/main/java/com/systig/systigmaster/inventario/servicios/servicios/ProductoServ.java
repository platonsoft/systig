package com.systig.systigmaster.inventario.servicios.servicios;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.google.gson.Gson;
import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.contable.entidades.Documento;
import com.systig.base.repositorios.inventario.entidades.Almacen;
import com.systig.base.repositorios.inventario.entidades.Categoria;
import com.systig.base.repositorios.inventario.oad.*;
import com.systig.base.repositorios.inventario.entidades.ItemProducto;
import com.systig.base.repositorios.inventario.entidades.Producto;
import com.systig.base.repositorios.nominas.entidades.EmpresaXPersona;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.base.repositorios.nominas.oad.IEmpresaXPersonaDao;
import com.systig.base.repositorios.nominas.oad.IPersonaDao;
import com.systig.systigmaster.inventario.servicios.interfaces.IProductosServ;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductoServ implements IProductosServ {

    private final IProductoDao iProductoDao;
    private final IAlmacenDao iAlmacenDao;
    private final ICategoriaDao iCategoriaDao;
    private final IEmpresaXPersonaDao iEmpresaXPersonaDao;
    private final IItemProductoDao iItemProductoDao;
    private final IPersonaDao iPersonaDao;

    public ProductoServ(IProductoDao iProductoDao, IAlmacenDao iAlmacenDao, ICategoriaDao iCategoriaDao, IEmpresaXPersonaDao iEmpresaXPersonaDao, IItemProductoDao iItemProductoDao, IPersonaDao iPersonaDao) {
        this.iProductoDao = iProductoDao;
        this.iAlmacenDao = iAlmacenDao;
        this.iCategoriaDao = iCategoriaDao;
        this.iEmpresaXPersonaDao = iEmpresaXPersonaDao;
        this.iItemProductoDao = iItemProductoDao;
        this.iPersonaDao = iPersonaDao;
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> getListadoMisProductos(HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();
                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(iItemProductoDao.findAllByIdProducto_IdPropietario_IdEmpresa(
                            empresaXPersona.get().getIdEmpresa().getIdEmpresa()));
                }else{
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
    public ResponseEntity<ResultadoTransaccion> getListadoMisProductosProveedor(HttpHeaders headers, Long idProveedor) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();
                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(iItemProductoDao.findAllByIdProducto_IdProveedor_IdEmpresaAndIdProducto_IdPropietario_IdEmpresa(
                            idProveedor,empresaXPersona.get().getIdEmpresa().getIdEmpresa()));
                }else{
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
    public ResponseEntity<ResultadoTransaccion> getListadoProductosProveedor(HttpHeaders headers, Long idProveedor) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();
                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(iItemProductoDao.findAllByIdProducto_IdProveedor_IdEmpresa(idProveedor));
                }else{
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
    public ResponseEntity<ResultadoTransaccion> getListadoProductos(HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();
                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(iItemProductoDao.findAllByIsPublicoIsTrue());
                }else{
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
    public ResponseEntity<ResultadoTransaccion> getListadoProductosDocumento(HttpHeaders headers, Long idDocumento) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(this.iItemProductoDao.findAllByIdDocumento_IdDocumento(idDocumento));
                }else{
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
    public ResponseEntity<ResultadoTransaccion> getProducto(HttpHeaders headers, Long idProducto) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(iItemProductoDao.getOne(idProducto));
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
    public ResponseEntity<ResultadoTransaccion> addProducto(HttpHeaders headers, Producto producto) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);

            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    producto.setIdPropietario(empresaXPersona.get().getIdEmpresa());
                    resultadoTransaccion.setResultado(this.iProductoDao.save(producto));
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
                }else {
                    resultadoTransaccion.setResultado("El Usuario no tiene empresa asociada, Primero registre una antes de continuar");
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
    public ResponseEntity<ResultadoTransaccion> addProductosItems(HttpHeaders headers, String strPeticion, Long idProducto) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Documento documento = null;
            ItemProducto[] itemsProductos = null;
            ObjectMapper mapper = new ObjectMapper();
            Gson gson = new Gson();

            Map<String, Object> peticion = mapper.readValue (strPeticion, new TypeReference<Map<String, Object>>() {});
            Persona usuario = iPersonaDao.statusSession(headers);

            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));

                if (peticion.containsKey("documento") && peticion.containsKey("listaItems")){
                    documento = gson.fromJson(peticion.get("documento").toString(),Documento.class);
                    itemsProductos = gson.fromJson(peticion.get("listaItems").toString(), ItemProducto[].class);
                }else {
                    throw new Exception("El objeto esta mal formado");
                }

                List<ItemProducto> itemProductos = new ArrayList<>();

                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    Producto producto = iProductoDao.getOne(idProducto);
                    for (ItemProducto itemProducto : itemsProductos) {
                        itemProducto.setIdDocumento(documento);
                        itemProducto.setIdEmpresaEnvios(0L);
                        itemProducto.setIdProducto(producto);
                    }
                    resultadoTransaccion.setResultado(this.iItemProductoDao.saveAll(itemProductos));
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
    public ResponseEntity<ResultadoTransaccion> setProducto(HttpHeaders headers, Producto producto, Long idProducto) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));

                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    producto.setIdPropietario(empresaXPersona.get().getIdEmpresa());
                    producto.setIdProducto(idProducto);
                    resultadoTransaccion.setResultado(this.iProductoDao.save(producto));
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
    public ResponseEntity<ResultadoTransaccion> delProducto(HttpHeaders headers, Long idProducto) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();
                if (empresaXPersona.isPresent()){
                    Optional<Producto> producto = iProductoDao.findAll().stream()
                            .filter(producto1 -> producto1.getIdProducto().equals(idProducto))
                            .filter(producto1 -> producto1.getIdPropietario().getIdEmpresa().equals(empresaXPersona.get().getIdEmpresa().getIdEmpresa()))
                            .findFirst();
                    if (producto.isPresent()){
                        this.iProductoDao.deleteById(producto.get().getIdProducto());
                        resultadoTransaccion.setResultado("Borrado Correcto");
                    }else{
                        resultadoTransaccion.setResultado("El producto que desea modificar no le pertenece");
                    }
                }else{
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
    public ResponseEntity<ResultadoTransaccion> getListadoAlmacenPropietario(HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));

                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(this.iAlmacenDao.findAllByIdPropietario_IdEmpresa(empresaXPersona.get().getIdEmpresa().getIdEmpresa()));
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
    public ResponseEntity<ResultadoTransaccion> getListadoCategoriaPropietario(HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));

                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                if (empresaXPersona.isPresent()){
                    resultadoTransaccion.setResultado(this.iCategoriaDao.findAllByIdPropietario_IdEmpresa(empresaXPersona.get().getIdEmpresa().getIdEmpresa()));
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

}
