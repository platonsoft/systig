package com.systig.systigmaster.inventario.servicios.servicios;

import com.google.gson.Gson;
import com.systig.systigmaster.inventario.repositorios.interfaces.IItemProductoDao;
import com.systig.systigmaster.inventario.repositorios.interfaces.IProductoDao;
import com.systig.systigmaster.inventario.repositorios.interfaces.IUsuarioDao;
import com.systig.systigmaster.inventario.repositorios.modelos.ItemProducto;
import com.systig.systigmaster.inventario.repositorios.modelos.Producto;
import com.systig.systigmaster.inventario.repositorios.modelos.ResultadoTransaccion;
import com.systig.systigmaster.inventario.repositorios.modelos.Usuario;
import com.systig.systigmaster.inventario.servicios.interfaces.IItemProductosServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemProductoService implements IItemProductosServ {

    private IUsuarioDao iUsuarioDao = new IUsuarioDao();
    private final IProductoDao iProductoDao;
    private final IItemProductoDao iItemProductoDao;

    public ItemProductoService(IProductoDao iProductoDao, IItemProductoDao iItemProductoDao) {
        this.iProductoDao = iProductoDao;
        this.iItemProductoDao = iItemProductoDao;
    }

    @Override
    public ResponseEntity<?> getListadoItemsProductoDocumento(HttpHeaders headers, Long idDocumento) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iItemProductoDao.findAllByIdDocumentoEquals(idDocumento));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> addItemProducto(HttpHeaders headers, ItemProducto itemProducto, Long idProducto, Long idDocumento) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            Producto productoAsociado = this.iProductoDao.getOne(idProducto);

            System.out.println("Item del Producto recibido --- > " + (new Gson()).toJson(itemProducto));

            if(usuario!=null){
                itemProducto.setIdProducto(productoAsociado);
                itemProducto.setIdDocumento(idDocumento);
                itemProducto.setCicloVida(0L);

                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iItemProductoDao.save(itemProducto));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> addItemsDocumento(HttpHeaders headers, List<Producto> productos, Long idDocumento) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);

            if(usuario!=null){
                List<ItemProducto> registros = new ArrayList<>();
                for (Producto item : productos) {
                    for (int i = 0; i < item.getCantidadExistencia().intValue(); i++) {
                        ItemProducto itemProducto = new ItemProducto();
                        itemProducto.setIdProducto(item);
                        itemProducto.setIdDocumento(idDocumento);
                        itemProducto.setMontoCompra(item.getMontoCompra());
                        itemProducto.setMontoVenta(item.getMontoCompra().multiply(item.getFactorGanancia()));
                        registros.add(itemProducto);
                    }
                }
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iItemProductoDao.saveAll(registros));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> UpdateItemProducto(HttpHeaders headers, Long idItemProducto, Long idDocumentoNuevo) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            ItemProducto itemProducto = iItemProductoDao.getOne(idItemProducto);

            Usuario usuario = iUsuarioDao.statusSession(headers);

            System.out.println("Item del Producto recibido --- > " + (new Gson()).toJson(itemProducto));

            if(usuario!=null){
                itemProducto.setIdDocumento(idDocumentoNuevo);
                itemProducto.setCicloVida(itemProducto.getCicloVida()+1);

                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iItemProductoDao.save(itemProducto));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> delItemProducto(HttpHeaders headers, Long idItemProducto, String observacion) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            ItemProducto itemProducto = iItemProductoDao.getOne(idItemProducto);

            Usuario usuario = iUsuarioDao.statusSession(headers);

            System.out.println("Item del Producto recibido --- > " + (new Gson()).toJson(itemProducto));

            // Verificar que el documento lo permite
            if(usuario!=null){
                itemProducto.setEliminado(1L);
                itemProducto.setObservacionEliminado(observacion);

                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iItemProductoDao.save(itemProducto));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getHistoriaItemProducto(HttpHeaders headers, Long idItemProducto) {
        return null;
    }
}
