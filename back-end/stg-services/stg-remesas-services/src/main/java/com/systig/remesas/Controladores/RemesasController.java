package com.systig.remesas.Controladores;

import com.systig.remesas.Inicializador;
import com.systig.remesas.Utilidades;
import com.systig.remesas.modelos.*;
import com.systig.remesas.seguridad.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/apirem")
public class RemesasController {

    @Autowired
    private IDispositivo iDispositivo;
    @Autowired
    private IUsuario iUsuario;
    @Autowired
    private INotificacion iNotificacion;
    @Autowired
    private ITransaccion iTransaccion;
    @Autowired
    private ICuenta iCuenta;
    @Autowired
    private IRemitente iRemitente;
    @Autowired
    private IReceptor iReceptor;


    /*
    * Muestra la Lista de todos los Dispositivos Registrados
    * */
    @GetMapping(path = "/usrs", produces = "application/json")
    public List<Dispositivo> ListaDispositivos(){
        return iDispositivo.findAll();
    }

    /*
     * Muestra la Lista de todos los Usuarios Registrados
     * */
    @GetMapping(path = "/usrs/list", produces = "application/json")
    public List<Usuario> ListaUsuarios(){
        return iUsuario.findAll();
    }

    /*
    * Muestra la lista de todas las transacciones
    * */
    @GetMapping(path = "/usr/trs", produces = "application/json")
    public List<Transaccion> ListaTransacciones(){
        return iTransaccion.findAll();
    }

    /*
     * Lo que hace en caso de error
     * */
    @GetMapping(path = "/error", produces = "application/json")
    public String Error(){
        return "Error de Acceso";
    }

    /*
    * Muestra la lista de las transacciones de un dispositivo
    * */
    @RequestMapping(value = "/usr/trs/{imei}/{cant}",method = RequestMethod.GET)
    public List<Transaccion> ListaTransaccionesPorImei(@PathParam("imei") Long imei,@PathParam("cant") Long cant){
        return iTransaccion.findAll().stream()
                .filter(tr -> tr.getImei().equals(imei))
                .sorted(Comparator.comparing(Transaccion::getFechaRegistro).reversed())
                .limit(cant)
                .collect(Collectors.toList());
    }

    /*
    * Muestra la Lista de las Notificaciones por Dispositivo
    * */
    @RequestMapping(value = "/usr/noti/{imei}",method = RequestMethod.GET)
    public List<Notificacion> ListaNoticias(@PathParam("imei") Long imei){
        return iNotificacion.findAll().stream()
                .filter(tr -> tr.getImei().equals(imei))
                .filter(noti -> !noti.getLeida())
                .sorted(Comparator.comparing(Notificacion::getFecha).reversed())
                .collect(Collectors.toList());
    }

    /*
     * Muestra las cuentas bancarias de los bancos disponibles por pais
     * */
    @RequestMapping(value = "/usr/bancos/{pais}",method = RequestMethod.GET)
    public List<Cuenta> ListaBancos(@PathParam("pais") Long pais){
        return iCuenta.findAll().stream()
                .filter(cu -> cu.getPais().equals(pais))
                .filter(cu2 -> cu2.getCodigo().equals("ESP"))
                .collect(Collectors.toList());
    }

    /*
    * Marca las transacciones completadas como solicitadas por pagar comision
    * */
    @RequestMapping(value = "/usr/pagar/{imei}", method = RequestMethod.GET)
    public Double SolicitudPago(@PathVariable("imei") Long imei){
        ActualizaCompletados();
        return iTransaccion.findAll().stream()
                .filter(t -> t.getImei().equals(imei))
                .mapToDouble(value -> value.getComisioPagar().doubleValue())
                .sum();
    }

    /*
    *  Solicita los datos de una transaccion especifica
    * */
    @RequestMapping(value = "/usr/tr/{codigo}", method = RequestMethod.GET)
    public Transaccion VerTransaccion(@PathVariable("codigo") String codigo){
        Transaccion tr = iTransaccion.findAll().stream()
                .filter(t -> t.getCodigo().equals(codigo))
                .findFirst()
                .get();
        return tr;
    }

    /*
    * Solicita la Tasa segun el pais del dispositivo
    * */
    @RequestMapping(value = "/usr/tasa/{imei}", method = RequestMethod.GET)
    public Double VerTasa(@PathVariable("imei") Long imei){
        String pais = iDispositivo.findAll().stream()
                .filter(d -> d.getImei().equals(imei))
                .findFirst()
                .get().getPais();

        return Utilidades.TasaVES(pais).doubleValue();
    }

    /*
    * Procedimiento para calcular y marcar las transacciones como solicitadas por pagar
    * */
    public void ActualizaCompletados(){
        List<Transaccion> g = iTransaccion.findAll().stream()
                .filter(tr -> tr.getStatus().equals("B"))//Transaccion Cambio Completada
                .map(t -> {
                    BigDecimal BV = t.getBitVenta();
                    BigDecimal BC = t.getBitCompra();
                    BigDecimal tasa2 = (BV.divide(BC, MathContext.DECIMAL64));
                    BigDecimal totalG = t.getMontoRecibido().multiply(tasa2).subtract(t.getMontoRecibido().multiply(t.getTasa())).divide(tasa2,MathContext.DECIMAL64);
                    t.setGananciaTotal(totalG);
                    t.setComisioPagar(totalG.multiply(new BigDecimal(0.35)));
                    t.setComisionRetenida(t.getGananciaTotal().subtract(t.getComisioPagar()));
                    t.setStatus("E");//Solicitada para pago
                    return t;
                })
                .collect(Collectors.toList());

        iTransaccion.saveAll(g);
    }

    @PostMapping(path = "/usr/login",consumes = "application/json")
    public String LoginDispositivo(@RequestBody Dispositivo dispositivo){
        String jwtToken = null;
        if (dispositivo.getUsuarioId()!=null){
            try {
                Usuario uu = iDispositivo.findAll().stream()
                        .filter(di -> di.getImei().equals(dispositivo.getImei()))
                        .filter(du -> du.getUsuarioId().getUsuario().equals(dispositivo.getUsuarioId().getUsuario()))
                        .filter(dc -> dc.getUsuarioId().getClave().equals(dispositivo.getUsuarioId().getClave()))
                        .map(dm -> dm.getUsuarioId())
                        .findFirst()
                        .get();
                jwtToken = Jwts.builder()
                        .setSubject(uu.getUsuario())
                        .claim("roles", "user")
                        .setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, "secretkey")
                        .compact();
            }catch (NoSuchElementException e){
                System.out.println("Las credenciales no coinciden");
            }
        }
        return jwtToken;
    }

    /*
    * Crea un nuevo Dispositivo y usuario, tambien queda marcado en remitentes
    * */
    @PostMapping(path = "/usr",consumes = "application/json")
    public Long CreateDispositivo(@RequestBody Dispositivo dispositivo){

        Cuenta cu = iCuenta.save(dispositivo.getUsuarioId().getDatosPersonales().getCuenta());
        Remitente rem = dispositivo.getUsuarioId().getDatosPersonales();
        rem.setCuenta(cu);

        Remitente remS = iRemitente.save(rem);

        Usuario uu = dispositivo.getUsuarioId();
        uu.setDatosPersonales(remS);

        dispositivo.setUsuarioId(iUsuario.save(uu));

        Long imei = iDispositivo.save(dispositivo).getImei();

        return imei;
    }

    /*
    * Crea una nueva transaccion de remesa con pago en efectivo
    * */
    @PostMapping(path = "/usr/rm/efc",consumes = "application/json")
    public String CreateTransaccionRemEfc(@RequestBody Transaccion transaccion){

        transaccion = AnalisisTransaccion(transaccion);

        Transaccion tr = iTransaccion.save(transaccion);
        tr.setCodigo("REM-EFC-" + tr.getIdTransaccion());

        return iTransaccion.save(tr).getCodigo();
    }

    /*
    * Crea una nueva transaccion de remesa con pago por transferencia
    * */
    @PostMapping(path = "/usr/rm/trf",consumes = "application/json")
    public String CreateTransaccionRemTrf(@RequestBody Transaccion transaccion){

        transaccion = AnalisisTransaccion(transaccion);

        Transaccion tr = iTransaccion.save(transaccion);
        tr.setCodigo("REM-TRF-" + tr.getIdTransaccion());

        return iTransaccion.save(tr).getCodigo();
    }


    /*
    * Crea una nueva transaccion de Deposito o pago en efectivo
    * */
    @PostMapping(path = "/usr/dep/efc",consumes = "application/json")
    public String CreateTransaccionDepoEfc(@RequestBody Transaccion transaccion){

        transaccion = AnalisisTransaccion(transaccion);

        Transaccion tr = iTransaccion.save(transaccion);
        tr.setCodigo("DEP-EFC-" + tr.getIdTransaccion());

        return iTransaccion.save(tr).getCodigo();
    }

    /*
    * Crea una nueva transaccion de Deposito con pago por transferencia
    * */
    @PostMapping(path = "/user/dep/trf",consumes = "application/json")
    public String CreateTransaccionDepoTrf(@RequestBody Transaccion transaccion){

        transaccion = AnalisisTransaccion(transaccion);

        Transaccion tr = iTransaccion.save(transaccion);
        tr.setCodigo("DEP-TRF-" + tr.getIdTransaccion());

        return iTransaccion.save(tr).getCodigo();
    }

    /*
    * Funcion para gestion de las transacciones
    * */
    private Transaccion AnalisisTransaccion(Transaccion transaccion){
        Dispositivo disp = iDispositivo.findAll().stream()
                .filter(dispositivo -> dispositivo.getImei().equals(transaccion.getImei()))
                .findFirst()
                .get();
        if (disp!=null){
            if (disp.getStatus()!=null){
                if(transaccion.getOtroRemitente()!=null){
                    try {
                        Remitente rem = iRemitente.findAll().stream()
                                .filter(remitente -> remitente.getNroDoc().equals(transaccion.getOtroRemitente().getNroDoc()))
                                .findFirst()
                                .get();

                        if (rem != null) {
                            Remitente rT = transaccion.getOtroRemitente();
                            rT.setIdRemitente(rem.getIdRemitente());
                            transaccion.setOtroRemitente(rT);
                        }
                    }catch (NoSuchElementException e){
                        Cuenta c = null;
                        if (transaccion.getOtroRemitente().getCuenta()!=null){
                            c = iCuenta.save(transaccion.getOtroRemitente().getCuenta());
                        }
                        transaccion.getOtroRemitente().setCuenta(c);
                        transaccion.setOtroRemitente(iRemitente.save(transaccion.getOtroRemitente()));
                    }

                }

                if (transaccion.getCliente()!=null){
                    try {
                        Receptor rc = iReceptor.findAll().stream()
                                .filter(receptor -> receptor.getNroDoc().equals(transaccion.getCliente().getNroDoc()))
                                .findFirst()
                                .get();
                        if (rc != null) {
                            Receptor rC = transaccion.getCliente();
                            rC.setId_receptor(rc.getId_receptor());
                            transaccion.setCliente(rC);
                        }
                    }catch (NoSuchElementException e){
                        Cuenta c = null;
                        if (transaccion.getOtroRemitente().getCuenta()!=null){
                            c = iCuenta.save(transaccion.getOtroRemitente().getCuenta());
                        }
                        transaccion.getCliente().setCuenta(c);
                        transaccion.setCliente(iReceptor.save(transaccion.getCliente()));
                    }
                }
                return transaccion;
            }
        }
        return null;
    }

    /*
    * Inicia la aplicacion con todos los datos necesarios
    * */
    @PostMapping(path = "/usr/valid",consumes = "application/json")
    public Inicializador ValidDispositivo(@RequestBody Dispositivo dispositivo){
        /*
        * Estados del usuario:
        *   1-Usuario nuevo: USNEW -> lIMITE DE TRANSACCIONES POR 15 DIAS NO MAYOR 50$
        *   2-Usuario Normal: USDEF -> lIMITE DE TRANSACCION POR 15 DIAS NO MAYOR A 100$
        *   3-Tasador Experto: USEXP -> SIN LIMITES
        *   4-bLOQUEADO: USBLK -> dispositivo bloqueado
        * */
        try {
            Dispositivo collect = iDispositivo.findAll().stream()
                    .filter(disp -> disp.getImei().equals(dispositivo.getImei()))
                    .findFirst()
                    .get();
            Inicializador ini1 = new Inicializador();
            ini1.setImei(dispositivo.getImei());
            ini1.setTasaDia(Utilidades.TasaVES(collect.getPais()));
            ini1.setPais(collect.getPais());
            List<Notificacion> notis = iNotificacion.findAll().stream()
                    .filter(notificacion -> {
                        return notificacion.getImei().equals(dispositivo.getImei());
                    })
                    .sorted(Comparator.comparing(Notificacion::getFecha).reversed())
                    .limit(20)
                    .collect(Collectors.toList());
            ini1.setNotificacions(notis);
            List<Transaccion> trans = iTransaccion.findAll().stream()
                    .filter(transaccion -> transaccion.getImei().equals(dispositivo.getImei()))
                    .sorted(Comparator.comparing(Transaccion::getFechaRegistro).reversed())
                    .limit(500)
                    .collect(Collectors.toList());
            ini1.setTransaccions(trans);
            return ini1;
        }catch (NoSuchElementException e) {
            return null;
        }
    }

    /*@DeleteMapping(path = "/user/{imei}",consumes = "application/json")
    public String DeleteDispositivo(@PathVariable("imei") String imei){
        return "";
    }

    @PutMapping(path = "/user/{imei}",consumes = "application/json")
    public String UpdateDispositivo(@PathVariable("imei") String imei, @RequestBody Dispositivo dispositivo) throws BadHttpRequest {
        return "";
    }*/
}
