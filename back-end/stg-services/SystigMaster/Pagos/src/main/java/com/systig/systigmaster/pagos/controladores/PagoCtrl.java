package com.systig.systigmaster.pagos.controladores;

import com.systig.base.repositorios.pay.entidades.Transaccion;
import com.systig.systigmaster.pagos.servicios.IPagosServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token","Authorization"})
public class PagoCtrl {

    private final IPagosServ iPagosServ;

    public PagoCtrl(IPagosServ iPagosServ) {
        this.iPagosServ = iPagosServ;
    }

    /*
     * Muestra la lista de las transacciones de un dispositivo
     * */
    @GetMapping("/api/pay/transacciones")
    public ResponseEntity<?> getListaTransacciones(@RequestHeader HttpHeaders headers) {
        return this.iPagosServ.getListaTransacciones(headers);
    }

    /*
     * Muestra la Lista de las Notificaciones por Dispositivo
     * */
    @GetMapping("/api/pay/notificaciones")
    public ResponseEntity<?> getListaNotificaciones(@RequestHeader HttpHeaders headers) {
        return this.iPagosServ.getListaNotificaciones(headers);
    }

    /*
     * Muestra las cuentas bancarias de los bancos disponibles por pais
     * */
    @GetMapping("/api/pay/bancos")
    public ResponseEntity<?> getListaBancos(@RequestHeader HttpHeaders headers) {
        return this.iPagosServ.getListaBancos(headers);
    }

    /*
     *  Solicita los datos de una transaccion especifica
     * */
    @GetMapping("/api/pay/transaccion/{idTransaccion}")
    public ResponseEntity<?> getTransaccion(@RequestHeader HttpHeaders headers, @PathVariable Long idTransaccion) {
        return this.iPagosServ.getTransaccion(idTransaccion, headers);
    }

    /*
     * Solicita la Tasa segun el pais del dispositivo
     * */
    @GetMapping("/api/pay/tasas")
    public ResponseEntity<?> getTasas(@RequestHeader HttpHeaders headers) {
        return this.iPagosServ.getTasas(headers);
    }

    /*
     * Crea una nueva transaccion de remesa
     * */
    @PostMapping(path = "/api/pay/transaccion",consumes = "application/json")
    public ResponseEntity<?> setTransaccion(@RequestHeader HttpHeaders headers, @RequestBody Transaccion transaccion){
        return this.iPagosServ.setTransaccion(transaccion, headers);
    }

    @PostMapping("/api/pay/transaccion/{idTransaccion}/{idAccion}")
    public ResponseEntity<?> setStatusTransaccion(@RequestHeader HttpHeaders headers, @PathVariable Long idTransaccion, @PathVariable Long idAccion) {
        return this.iPagosServ.setTransaccion(idTransaccion, idAccion, headers);
    }
}
