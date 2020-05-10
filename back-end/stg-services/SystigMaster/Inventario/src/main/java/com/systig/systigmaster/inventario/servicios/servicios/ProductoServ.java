package com.systig.systigmaster.inventario.servicios.servicios;

import com.google.gson.Gson;
import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.contable.oad.IHistoriaDao;
import com.systig.base.repositorios.inventario.entidades.Almacen;
import com.systig.base.repositorios.inventario.entidades.Categoria;
import com.systig.base.repositorios.inventario.oad.*;
import com.systig.base.repositorios.inventario.entidades.ItemProducto;
import com.systig.base.repositorios.inventario.entidades.Producto;
import com.systig.base.repositorios.sesiones.oad.IUsuarioDao;
import com.systig.systigmaster.inventario.servicios.interfaces.IProductosServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServ implements IProductosServ {

    private final IProductoDao iProductoDao;
    private final IAlmacenDao iAlmacenDao;
    private final ICategoriaDao iCategoriaDao;

    private final IItemProductoDao iItemProductoDao;
    private final IHistoriaDao iHistoriaDao;
    private final IUsuarioDao iUsuarioDao;

    public ProductoServ(IProductoDao iProductoDao, IAlmacenDao iAlmacenDao, ICategoriaDao iCategoriaDao, IItemProductoDao iItemProductoDao, IHistoriaDao iHistoriaDao, IUsuarioDao iUsuarioDao) {
        this.iProductoDao = iProductoDao;
        this.iAlmacenDao = iAlmacenDao;
        this.iCategoriaDao = iCategoriaDao;
        this.iItemProductoDao = iItemProductoDao;
        this.iHistoriaDao = iHistoriaDao;
        this.iUsuarioDao = iUsuarioDao;
    }

    @Override
    public ResponseEntity<?> getListadoProductos(HttpHeaders headers) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iProductoDao.findAllByIdPropietarioEquals(usuario.getEmpresa().getIdPropietario()));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            // e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getListadoProductosProveedor(HttpHeaders headers, Long idProveedor) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iProductoDao.findAllByIdPropietarioEqualsAndIdProveedorEquals(usuario.getEmpresa().getIdPropietario(), idProveedor));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            // e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getListadoProductosDocumento(HttpHeaders headers, Long idDocumento) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iItemProductoDao.findAllByIdDocumentoEquals(idDocumento));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            // e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getProducto(HttpHeaders headers, Long idProducto) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iProductoDao.getByIdProducto(idProducto));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            // e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> addProducto(HttpHeaders headers, Producto producto) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);

            System.out.println("Producto recibido --- > " + (new Gson()).toJson(producto));

            if(usuario!=null){

                Almacen almacen = iAlmacenDao.getFirstByNombreEqualsAndIdPropietarioEquals(producto.getAlmacen().getNombre().toUpperCase(),usuario.getEmpresa().getIdPropietario());
                Categoria categoria = iCategoriaDao.getFirstByNombreEqualsAndIdPropietarioEquals(producto.getCategoria().getNombre().toUpperCase(),usuario.getEmpresa().getIdPropietario());

                if (almacen==null){
                    producto.getAlmacen().setIdPropietario(usuario.getEmpresa().getIdPropietario());
                    producto.setAlmacen(iAlmacenDao.save(producto.getAlmacen()));
                }

                if (categoria ==null){
                    producto.getCategoria().setIdPropietario(usuario.getEmpresa().getIdPropietario());
                    producto.setCategoria(iCategoriaDao.save(producto.getCategoria()));
                }

                producto.setIdPropietario(usuario.getEmpresa().getIdPropietario());
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iProductoDao.save(producto));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            // e.printStackTrace();
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> addProductosItems(HttpHeaders headers, List<Producto> productos, Long idDocumento) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);

            //System.out.println("Producto recibido --- > " + (new Gson()).toJson(producto));

            if(usuario!=null){
                List<ItemProducto> itemProductos = new ArrayList<>();
                for (Producto producto : productos) {
                    ItemProducto item = new ItemProducto();
                    item.setUnidad(producto.getUnidad());
                    item.setIdDocumento(idDocumento);
                    item.setMontoCompra(producto.getMontoCompra());
                    item.setIdEmpresaEnvios(0L);
                    item.setCantidad(producto.getCantidadExistencia());
                    item.setIdProducto(producto);
                    itemProductos.add(item);
                }
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iItemProductoDao.saveAll(itemProductos));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            // e.printStackTrace();
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> setProducto(HttpHeaders headers, Producto producto, Long idProducto) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                System.out.println(idProducto + " - Producto recibido --- > " + (new Gson()).toJson(producto));
                producto.setIdPropietario(usuario.getEmpresa().getIdPropietario());
                producto.setIdProducto(idProducto);
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iProductoDao.save(producto));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Actualizacion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            // e.printStackTrace();
            return new ResponseEntity<>("Actualizacion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> delProducto(HttpHeaders headers, Long idProducto) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                this.iProductoDao.deleteById(idProducto);
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado("Borrado Correcto");
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            // e.printStackTrace();
            return new ResponseEntity<>("Borrado Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getHistoriaProducto(HttpHeaders headers, Long idProducto) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iHistoriaDao.findAllByElementoEquals(String.valueOf(idProducto)));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            // e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getListadoAlmacenPropietario(HttpHeaders headers) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iAlmacenDao.findAllByIdPropietarioEquals(usuario.getEmpresa().getIdPropietario()));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            // e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getListadoCategoriaPropietario(HttpHeaders headers) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iCategoriaDao.findAllByIdPropietarioEquals(usuario.getEmpresa().getIdPropietario()));
                return new ResponseEntity<ResultadoTransaccion>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            // e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

}
