package com.systig.systigmaster.pagos.controladores;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.systigmaster.pagos.servicios.IPagosServ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins="*", maxAge=3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token","Authorization"})
@Api(tags = {"Pagos"})
public class PagoCtrl {

    private final IPagosServ iPagosServ;

    public PagoCtrl(IPagosServ iPagosServ) {
        this.iPagosServ = iPagosServ;
    }

    @GetMapping("/api/pay/transacciones")
    @ApiOperation(value = "Muestra la lista de las transacciones de un dispositivo")
    public ResponseEntity<ResultadoTransaccion> getListaTransacciones(@RequestHeader HttpHeaders headers) {
        return this.iPagosServ.getListaTransacciones(headers);
    }

    /*
     *
     * */
    @GetMapping("/api/pay/saldo")
    @ApiOperation(value = "Muestra el saldo del cliente")
    public ResponseEntity<ResultadoTransaccion> getSaldo(@RequestHeader HttpHeaders headers) {
        return this.iPagosServ.getSaldo(headers);
    }

    /*
     *
     * */
    @GetMapping("/api/pay/notificaciones")
    @ApiOperation(value = "Muestra la Lista de las Notificaciones por Dispositivo")
    public ResponseEntity<ResultadoTransaccion> getListaNotificaciones(@RequestHeader HttpHeaders headers) {
        return this.iPagosServ.getListaNotificaciones(headers);
    }

    @GetMapping("/api/pay/bancos")
    @ApiOperation(value = "Muestra las cuentas bancarias de los bancos disponibles por pais")
    public ResponseEntity<ResultadoTransaccion> getListaBancos(@RequestHeader HttpHeaders headers) {
        return this.iPagosServ.getListaBancos(headers);
    }

    @GetMapping("/api/pay/transaccion/{idTransaccion}")
    @ApiOperation(value = "Solicita los datos de una transaccion especifica")
    public ResponseEntity<ResultadoTransaccion> getTransaccion(@RequestHeader HttpHeaders headers, @PathVariable Long idTransaccion) {
        return this.iPagosServ.getTransaccion(idTransaccion, headers);
    }

    @GetMapping("/api/pay/tasas")
    @ApiOperation(value = "Solicita la Tasa segun el pais del dispositivo")
    public ResponseEntity<ResultadoTransaccion> getTasas(@RequestHeader HttpHeaders headers) {
        return this.iPagosServ.getTasas(headers);
    }

    @PostMapping(path = "/api/pay/transaccion",consumes = "application/json")
    @ApiOperation(value = "Crea una nueva transaccion de remesa")
    public ResponseEntity<ResultadoTransaccion> addTransaccion(@RequestHeader HttpHeaders headers, @RequestBody String transaccion){
        return this.iPagosServ.addTransaccion(transaccion, headers);
    }

    @PostMapping("/api/pay/transaccion/{codReferencia}/{monto}")
    @ApiOperation(value = "Confirma que un pago ha sido realizado")
    public ResponseEntity<ResultadoTransaccion> confirmarPago(@RequestHeader HttpHeaders headers, @PathVariable String codReferencia, @PathVariable BigDecimal monto) {
        return this.iPagosServ.confirmarTransaccion(codReferencia, monto, headers);
    }
}
