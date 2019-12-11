package com.systig.systigmaster.contable.servicios.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.systig.systigmaster.contable.repositorios.interfaces.IDocumentoDao;
import com.systig.systigmaster.contable.repositorios.interfaces.IHistoriaDao;
import com.systig.systigmaster.contable.repositorios.interfaces.IUsuarioDao;
import com.systig.systigmaster.contable.repositorios.modelos.Documento;
import com.systig.systigmaster.contable.repositorios.modelos.Pago;
import com.systig.systigmaster.contable.repositorios.modelos.ResultadoTransaccion;
import com.systig.systigmaster.contable.repositorios.modelos.Usuario;
import com.systig.systigmaster.contable.servicios.interfaces.IDocumentosServ;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DocumentoServ implements IDocumentosServ {

    private IUsuarioDao iUsuarioDao = new IUsuarioDao();
    private final IDocumentoDao iDocumentoDao;
    private final IHistoriaDao iHistoriaDao;

    public DocumentoServ(IDocumentoDao iDocumentoDao, IHistoriaDao iHistoriaDao) {
        this.iDocumentoDao = iDocumentoDao;
        this.iHistoriaDao = iHistoriaDao;
    }

    @Override
    public ResponseEntity<?> getListaDocumentos(HttpHeaders headers, HttpSession session, TIPO_DOCUMENTO tipoDocumento) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iDocumentoDao.findAllByTipoDocumentoEqualsAndIdPropietarioEquals(tipoDocumento.getTipoDocumento(), usuario.getPropietario().getIdPropietario()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getAllListaDocumentos(HttpHeaders headers, HttpSession session) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iDocumentoDao.findAllByIdPropietarioEquals(usuario.getPropietario().getIdPropietario()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
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
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iDocumentoDao.getOne(idDocumento));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> addDocumento(HttpHeaders headers, HttpSession session, Map<String, Object> documento) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);

            System.out.println("Producto recibido --- > " + (new Gson()).toJson(documento));

            if(usuario!=null){
                ObjectMapper mapper = new ObjectMapper();
                Gson json = new Gson();

                Documento doc = mapper.convertValue(documento.get("documento"), Documento.class);
                Pago pago = mapper.convertValue(documento.get("pago"), Pago.class);
                String productos = json.toJson(documento.get("productos"));

                RestTemplate productosTemplate = new RestTemplate();
                HttpEntity<String> entity = new HttpEntity<String>(productos, headers);

                ResponseEntity<String> productosRessiltado = productosTemplate.exchange("", HttpMethod.POST,entity,String.class);

                doc.setIdPropietario(usuario.getPropietario().getIdPropietario());

                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iDocumentoDao.save(doc));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Insersion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> setDocumento(HttpHeaders headers, HttpSession session, Documento documento, Long idDocumento) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                System.out.println(idDocumento + " - Producto recibido --- > " + (new Gson()).toJson(documento));
                documento.setIdPropietario(usuario.getPropietario().getIdPropietario());
                documento.setIdDocumento(idDocumento);
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iDocumentoDao.save(documento));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Actualizacion Fallida", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Actualizacion Fallida", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getHistoriaDocumentos(HttpHeaders headers, HttpSession session, Long idDocumento) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iHistoriaDao.findAllByElementoEquals(String.valueOf(idDocumento)));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

}
