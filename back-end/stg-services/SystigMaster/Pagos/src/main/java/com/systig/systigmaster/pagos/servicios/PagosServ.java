package com.systig.systigmaster.pagos.servicios;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.nominas.entidades.Pais;
import com.systig.base.repositorios.nominas.oad.IEntidadFinancieraDao;
import com.systig.base.repositorios.nominas.oad.IPaisDao;
import com.systig.base.repositorios.pay.entidades.Tasa;
import com.systig.base.repositorios.pay.entidades.Transaccion;
import com.systig.base.repositorios.pay.oad.ITransaccionDao;
import com.systig.base.repositorios.sesiones.oad.INotificacionDao;
import com.systig.base.repositorios.sesiones.oad.IUsuarioDao;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PagosServ implements IPagosServ {

    private final IUsuarioDao iUsuarioDao;
    private final ITransaccionDao iTransaccionDao;
    private final INotificacionDao iNotificacionDao;
    private final IEntidadFinancieraDao iEntidadFinancieraDao;
    private final IPaisDao iPaisDao;

    public PagosServ(
            IUsuarioDao iUsuarioDao,
            ITransaccionDao iTransaccionDao,
            INotificacionDao iNotificacionDao,
            IEntidadFinancieraDao iEntidadFinancieraDao,
            IPaisDao iPaisDao) {
        this.iUsuarioDao = iUsuarioDao;
        this.iTransaccionDao = iTransaccionDao;
        this.iNotificacionDao = iNotificacionDao;
        this.iEntidadFinancieraDao = iEntidadFinancieraDao;
        this.iPaisDao = iPaisDao;
    }

    @Override
    public ResponseEntity<?> getListaTransacciones(HttpHeaders headers) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iTransaccionDao.findAllByIdRemitenteOrIdClienteEquals(usuario.getIdUsuario().toString(), usuario.getIdUsuario().toString()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getListaNotificaciones(HttpHeaders headers) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iNotificacionDao.findByIdUsuario_IdUsuario(usuario.getIdUsuario()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getListaBancos(HttpHeaders headers) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iEntidadFinancieraDao.findAllByIdPaisEquals(usuario.getPersona().getIdPais()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getTransaccion(Long idTransaccion, HttpHeaders headers) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iTransaccionDao.getByIdTransaccionEquals(idTransaccion));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> getTasas(HttpHeaders headers) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Usuario usuario = iUsuarioDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iUsuarioDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(calculoTasas(usuario.getPersona().getIdPais()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> setTransaccion(Transaccion transaccion, HttpHeaders headers) {
        return null;
    }

    @Override
    public ResponseEntity<?> setTransaccion(Long idTransaccion, Long idAccion, HttpHeaders headers) {
        return null;
    }


    List<Tasa> calculoTasas(Long idPais){
        Pais pais = iPaisDao.getByIdPaisEquals(idPais);
        if (pais.getIsDisponible()){
            RestTemplate restTemplate = new RestTemplate();
            String Url = "https://localbitcoins.com/sell-bitcoins-online/";
            ResponseEntity<String> response = restTemplate.getForEntity(Url + pais.getCodPais() + "/" + pais.getNombre() + "/.json", String.class);

            System.out.println("resultado: " + response.getBody());
        }
        return null;
    }
}
