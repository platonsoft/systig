package com.systig.systigmaster.pagos.servicios;

import com.google.gson.Gson;
import com.systig.base.objetos.Oferta;
import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.contable.entidades.Documento;
import com.systig.base.repositorios.contable.oad.IDocumentoDao;
import com.systig.base.repositorios.nominas.entidades.CuentaEntidad;
import com.systig.base.repositorios.nominas.entidades.EmpresaXPersona;
import com.systig.base.repositorios.nominas.entidades.Pais;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.base.repositorios.nominas.oad.*;
import com.systig.base.repositorios.pay.entidades.Tasa;
import com.systig.base.repositorios.pay.entidades.Transaccion;
import com.systig.base.repositorios.pay.oad.ITransaccionDao;
import com.systig.base.repositorios.sesiones.oad.INotificacionDao;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class PagosServ implements IPagosServ {

    private final IPersonaDao iPersonaDao;
    private final ITransaccionDao iTransaccionDao;
    private final INotificacionDao iNotificacionDao;
    private final IEntidadFinancieraDao iEntidadFinancieraDao;
    private final ICuentaEntidadDao iCuentaEntidadDao;
    private final IPaisDao iPaisDao;
    private final IDocumentoDao iDocumentoDao;
    private final IEmpresaXPersonaDao iEmpresaXPersonaDao;

    public PagosServ(IPersonaDao iPersonaDao, ITransaccionDao iTransaccionDao, INotificacionDao iNotificacionDao, IEntidadFinancieraDao iEntidadFinancieraDao, ICuentaEntidadDao iCuentaEntidadDao, IPaisDao iPaisDao, IDocumentoDao iDocumentoDao, IEmpresaXPersonaDao iEmpresaXPersonaDao) {
        this.iPersonaDao = iPersonaDao;
        this.iTransaccionDao = iTransaccionDao;
        this.iNotificacionDao = iNotificacionDao;
        this.iEntidadFinancieraDao = iEntidadFinancieraDao;
        this.iCuentaEntidadDao = iCuentaEntidadDao;
        this.iPaisDao = iPaisDao;
        this.iDocumentoDao = iDocumentoDao;
        this.iEmpresaXPersonaDao = iEmpresaXPersonaDao;
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> getListaTransacciones(HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iTransaccionDao.findAllByIdPersonaEquals(usuario.getIdPersona()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            resultadoTransaccion.setResultado("Acceso denegado");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> getListaNotificaciones(HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iNotificacionDao.findByIdPersona_IdPersona(usuario.getIdPersona()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            resultadoTransaccion.setResultado("Acceso denegado");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> getListaBancos(HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iEntidadFinancieraDao.findAllByPais_IdPais(usuario.getPais().getIdPais()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            resultadoTransaccion.setResultado("Acceso denegado");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> getTransaccion(Long idTransaccion, HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iTransaccionDao.getByIdTransaccionEquals(idTransaccion));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            resultadoTransaccion.setResultado("Acceso denegado");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> getTasas(HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(calculoTasas(usuario.getPais().getNombre()));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            resultadoTransaccion.setResultado("Acceso denegado");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> getSaldo(HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(calculoSaldo(usuario.getIdPersona()));
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

    private BigDecimal calculoSaldo(Long idPersona){
        BigDecimal saldoTotal = new BigDecimal(0);
        List<BigDecimal> montosTransacciones = iTransaccionDao.findAll()
                .stream()
                .filter(transaccion1 -> transaccion1.getIdPersona().equals(idPersona))
                .filter(transaccion1 -> transaccion1.getStatus().equals("I"))
                .map(Transaccion::getMontoRecibido)
                .collect(toList());

        for (BigDecimal val : montosTransacciones) {
            if (val!=null){
                saldoTotal = saldoTotal.add(val);
            }
        }
        return saldoTotal;
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> addTransaccion(String transaccion, HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);

            if(usuario!=null){
                Transaccion trn = (new Gson()).fromJson(transaccion, Transaccion.class);

                if (trn.getCodigo().substring(0,2).equals("R0")){ //Recarga en efectivo o transferencia
                    return recargaEfectivoTransferencia(trn, usuario);
                } else if (trn.getCodigo().substring(0,2).equals("R1")){ //Recarga con Tarjeta de Credito
                    resultadoTransaccion.setResultado("Funcion no implementada aun");
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.LOCKED);
                } else if (trn.getCodigo().substring(0,2).equals("R2")){ //Recarga por transferencia bancaria con PSE
                    resultadoTransaccion.setResultado("Funcion no implementada aun");
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.LOCKED);
                } else if (trn.getCodigo().substring(0,2).equals("T0")) { // Transferencia
                    return transferencia(trn,usuario);
                } else if (trn.getCodigo().substring(0,2).equals("L0")) { // Liberar Dinero
                    return liberarCartera(trn,usuario);
                }else {
                    resultadoTransaccion.setResultado("Funcion no implementada aun");
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.LOCKED);
                }
            }else{
                resultadoTransaccion.setResultado("Acceso denegado");
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            e.printStackTrace();
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResultadoTransaccion> confirmarTransaccion(String refOperacion, BigDecimal montoConfirmado, HttpHeaders headers) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);

            if(usuario!=null){
                Optional<Transaccion> opTransaccion = iTransaccionDao.findAll().stream()
                        .filter(transaccion1 -> transaccion1.getRefOperacion().equals(refOperacion))
                        .filter(transaccion2 -> transaccion2.getStatus().equals("G"))
                        .filter(transaccion3 -> transaccion3.getCodigo().substring(0,2).equals("R0")) //Recarga en efectivo o transferencia
                        .findFirst();
                if (opTransaccion.isPresent()){
                    Transaccion trans = opTransaccion.get();
                    String[] descripcion = trans.getDescripcion().split("/");
                    Oferta oferta = descripcion.length == 4 ? CalculoPrecioCompra(montoConfirmado,descripcion[1],usuario.getPais().getCodMoneda()): null;

                    if (oferta!=null){
                        trans.setUsuarioConfirmacion(usuario.getIdPersona());
                        trans.setFechaConfirmacion(LocalDateTime.now());
                        trans.setStatus("I");
                        trans.setMontoRecibido(montoConfirmado.multiply(new BigDecimal(1)));

                        trans.setBitCompra(oferta.getPrecioBTC().setScale(8,RoundingMode.DOWN));
                        trans.setComisionRetenida(BigDecimal.ZERO);

                        BigDecimal btcVenta = montoConfirmado.divide(oferta.getPrecioBTC(),8, RoundingMode.DOWN);
                        BigDecimal tasaReal = oferta.getPrecioBTC().divide(oferta.getPrecioBTC(),8,RoundingMode.DOWN);
                        BigDecimal tasaOferta = oferta.getPrecioBTC().divide(oferta.getPrecioBTC(),8, RoundingMode.DOWN).multiply(BigDecimal.ONE).setScale(2,RoundingMode.DOWN);
                        BigDecimal factorGanancia = BigDecimal.ZERO;

                        trans.setTasa(tasaOferta);
                        trans.setLinkCompra(oferta.getLinkAcceso());
                        trans.setTasaReal(tasaReal);
                        trans.setGananciaTotal(factorGanancia.multiply(btcVenta));

                        System.out.println("Transacc - > " + trans.toString());

                        resultadoTransaccion.setResultado(iTransaccionDao.save(trans));
                        resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                        return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
                    }else{
                        resultadoTransaccion.setResultado("No hay ofertas para este monto, intente mas tarde");
                        return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
                    }
                } else {
                    resultadoTransaccion.setResultado("La transaccion no fue encontrada");
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.NOT_ACCEPTABLE);
                }
            }else{
                resultadoTransaccion.setResultado("Acceso denegado");
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ResultadoTransaccion> recargaEfectivoTransferencia(Transaccion nuevaTransaccion, Persona usuario){
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try {
            Optional<Transaccion> opTransaccion = iTransaccionDao.findAll().stream()
                    .filter(transaccion1 -> nuevaTransaccion.getNroReferencia().equals(transaccion1.getNroReferencia()))
                    .findFirst();
            if (opTransaccion.isPresent()){
                return null;
            }
            nuevaTransaccion.setStatus("G");
            nuevaTransaccion.setIdPersona(usuario.getIdPersona());
            nuevaTransaccion.setFechaRegistro(LocalDateTime.now());
            nuevaTransaccion.setRefOperacion(genNroReferencia());

            resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
            resultadoTransaccion.setResultado(iTransaccionDao.save(nuevaTransaccion));

            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ResultadoTransaccion> transferencia(Transaccion nuevaTransaccion , Persona usuario){
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try {
            String nroOperacion = genNroReferencia();

            Transaccion finalNuevaTransaccion = nuevaTransaccion;
            Optional<Persona> opPersona = iPersonaDao.findAll().stream()
                    .filter(persona -> persona.getEmail().equals(finalNuevaTransaccion.getNroReferencia()))
                    .findFirst();
            if (!opPersona.isPresent()){
                resultadoTransaccion.setResultado("No existe el usuario");
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.NOT_ACCEPTABLE);
            }

            if (nuevaTransaccion.getMontoRecibido().doubleValue() > 0) {
                nuevaTransaccion.setStatus("I");
                nuevaTransaccion.setFechaRegistro(LocalDateTime.now());
                nuevaTransaccion.setRefOperacion(nroOperacion);
                nuevaTransaccion.setIdPersona(opPersona.get().getIdPersona());

                nuevaTransaccion = calculoTasaTransferencias(usuario.getPais().getIdPais(),
                        opPersona.get().getPais().getIdPais(),
                        nuevaTransaccion);

                nuevaTransaccion = iTransaccionDao.save(nuevaTransaccion);
                Transaccion trn2 = new Transaccion();
                trn2.setIdTransaccion(null);
                trn2.setDescripcion(nuevaTransaccion.getDescripcion());
                trn2.setCodigo(nuevaTransaccion.getCodigo());
                trn2.setIdPersona(usuario.getIdPersona());
                trn2.setNroReferencia(opPersona.get().getEmail());
                trn2.setMontoRecibido(nuevaTransaccion.getMontoRecibido().multiply(new BigDecimal(-1)));
                trn2.setBitVenta(nuevaTransaccion.getBitVenta());
                trn2.setBitCompra(nuevaTransaccion.getBitCompra());
                trn2.setTasaReal(nuevaTransaccion.getTasaReal());
                trn2.setTasa(nuevaTransaccion.getTasa());
                trn2.setGananciaTotal(nuevaTransaccion.getGananciaTotal());
                trn2.setRefOperacion(nroOperacion);
                trn2.setStatus(nuevaTransaccion.getStatus());
                trn2.setLinkVenta(nuevaTransaccion.getLinkVenta());
                trn2.setLinkCompra(nuevaTransaccion.getLinkCompra());

                iTransaccionDao.save(trn2);
            }

            resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
            resultadoTransaccion.setResultado(iTransaccionDao.save(nuevaTransaccion));

            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<ResultadoTransaccion> liberarCartera(Transaccion nuevaTransaccion , Persona usuario){
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();

        try{
            Optional<CuentaEntidad> cuentaEntidad = iCuentaEntidadDao.findAll().stream()
                    .filter(cuentaEntidad1 -> cuentaEntidad1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                    .findFirst();

            if (cuentaEntidad.isPresent()){
                nuevaTransaccion.setStatus("I");
                nuevaTransaccion.setFechaRegistro(LocalDateTime.now());
                nuevaTransaccion.setRefOperacion(genNroReferencia());
                nuevaTransaccion.setIdPersona(usuario.getIdPersona());
                nuevaTransaccion.setNroReferencia(usuario.getEmail());
                nuevaTransaccion.setMontoRecibido(nuevaTransaccion.getMontoRecibido().multiply(new BigDecimal(-1)));
                String descripcion = cuentaEntidad.get().getTipoCuenta().concat("/")
                        .concat(cuentaEntidad.get().getEntidadFinanciera().getNombreBanco()).concat("/")
                        .concat(cuentaEntidad.get().getNroCuenta()).concat("/")
                        .concat(usuario.getNombres().concat("/").concat(usuario.getApellidos())).concat("/")
                        .concat(usuario.getTipoIdentificacion().getAbrevDoc().concat("/").concat(usuario.getNroIdentificacion()));

                nuevaTransaccion.setDescripcion(descripcion);
            }else {
                resultadoTransaccion.setResultado("Usted no posee una cuenta bancaria asociada para retirar su dinero, afiliela o llame al centro de ayuda");
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.NOT_ACCEPTABLE);
            }

            resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
            resultadoTransaccion.setResultado(iTransaccionDao.save(nuevaTransaccion));

            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            resultadoTransaccion.setResultado("Error Interno, Contacte al administrador del sistema");
            return new ResponseEntity<>(resultadoTransaccion, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private CuentaEntidad getCuentaAdministrador(Long idPais){
        Optional<Persona> persona = iPersonaDao.findAll().stream()
                .filter(persona1 -> persona1.getRol()==1)
                .filter(persona1 -> persona1.getPais().getIdPais()==idPais)
                .findFirst();

        if (persona.isPresent()){
            Optional<CuentaEntidad> cuentaEntidad = iCuentaEntidadDao.findAll().stream()
                    .filter(cuentaEntidad1 -> cuentaEntidad1.getIdPersona().getIdPersona() == persona.get().getIdPersona())
                    .findFirst();
            if (cuentaEntidad.isPresent()){
                return cuentaEntidad.get();
            }else{
                return null;
            }
        }
        return null;
    }

    private Transaccion calculoTasaTransferencias(Long idPaisOrigen, Long idPaisDestino, Transaccion transaccion){
        try{
            CuentaEntidad cuentaBase = getCuentaAdministrador(idPaisOrigen);
            CuentaEntidad cuentaDestino = getCuentaAdministrador(idPaisDestino);

            String bancoCompra = cuentaBase.getEntidadFinanciera().getNombreBanco();
            String bancoVenta = cuentaDestino.getEntidadFinanciera().getNombreBanco();

            Oferta oCompra = CalculoPrecioCompra(transaccion.getMontoRecibido(), bancoCompra, cuentaBase.getIdPersona().getPais().getCodMoneda());
            transaccion.setBitCompra(transaccion.getMontoRecibido().divide(oCompra.getPrecioBTC(),8, RoundingMode.DOWN));
            Oferta oVenta = CalculoPrecioVenta(transaccion.getBitCompra(), bancoVenta, cuentaDestino.getIdPersona().getPais().getCodMoneda());
            transaccion.setTasaReal(oVenta.getPrecioBTC().divide(oCompra.getPrecioBTC(),8,RoundingMode.DOWN));
            transaccion.setTasa(oVenta.getPrecioBTC().divide(oCompra.getPrecioBTC(),8,RoundingMode.DOWN).multiply(new BigDecimal(0.98)).setScale(2,RoundingMode.DOWN));
            transaccion.setBitVenta(oCompra.getMontoAccion().multiply(transaccion.getTasa()).setScale(0,RoundingMode.DOWN).divide(oVenta.getPrecioBTC(),8,RoundingMode.DOWN));
            transaccion.setGananciaTotal(transaccion.getBitCompra().subtract(transaccion.getBitVenta()));
            transaccion.setLinkCompra(oCompra.getLinkAcceso());
            transaccion.setLinkVenta(oVenta.getLinkAcceso());
            return transaccion;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    List<Tasa> calculoTasas(String idPais){
        Pais pais = iPaisDao.getByNombreEquals(idPais);
        if (pais.getIsDisponible()){
            RestTemplate restTemplate = new RestTemplate();
            String Url = "https://localbitcoins.com/sell-bitcoins-online/";
            ResponseEntity<String> response = restTemplate.getForEntity(Url + pais.getCodPais() + "/" + pais.getNombre() + "/.json", String.class);

            System.out.println("resultado: " + response.getBody());
        }
        return null;
    }






    public ResponseEntity<ResultadoTransaccion> getListaDocumentos(HttpHeaders headers, HttpSession session, TIPO_DOCUMENTO tipoDocumento) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                List<Documento> list = this.iDocumentoDao.findAll().stream()
                        .filter(documento -> documento.getTipoDocumento().equals(tipoDocumento.getTipoDocumento()))
                        .collect(Collectors.toList());

                resultadoTransaccion.setResultado(list);
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

    public ResponseEntity<ResultadoTransaccion> getDocumento(HttpHeaders headers, HttpSession session, Long idDocumento) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        try{
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iDocumentoDao.getOne(idDocumento));
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
