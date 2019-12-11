package com.systig.systigmaster.contable.servicios.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.systig.systigmaster.contable.repositorios.interfaces.IDocumentoDao;
import com.systig.systigmaster.contable.repositorios.interfaces.IHistoriaDao;
import com.systig.systigmaster.contable.repositorios.interfaces.IPago;
import com.systig.systigmaster.contable.repositorios.interfaces.IUsuarioDao;
import com.systig.systigmaster.contable.repositorios.modelos.*;
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
    private final IPago iPago;
    private Configuracion configuracion;

    public DocumentoServ(IDocumentoDao iDocumentoDao, IHistoriaDao iHistoriaDao, IPago iPago) {
        this.iDocumentoDao = iDocumentoDao;
        this.iHistoriaDao = iHistoriaDao;
        this.iPago = iPago;
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

                doc.setIdPropietario(usuario.getPropietario().getIdPropietario());

                Documento docResultado = this.iDocumentoDao.save(doc);
                pago.setIdDocumento(docResultado);

                RestTemplate productosTemplate = new RestTemplate();
                HttpEntity<String> entity = new HttpEntity<String>(productos, headers);

                ResponseEntity<ResultadoTransaccion> productosRessiltado = productosTemplate.exchange(configuracion.getUrlInventario()
                                                             .concat("/api/inv/producto/items/").concat(docResultado.getIdDocumento().toString()),
                                                             HttpMethod.POST,entity,ResultadoTransaccion.class);
                Pago pagoResultado = iPago.save(pago);

                documento.replace("documento", json.toJson(docResultado));
                documento.replace("productos", json.toJson(productosRessiltado));
                documento.replace("pago", json.toJson(pagoResultado));

                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(documento);
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
