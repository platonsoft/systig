package com.systig.systigmaster.contable.controladores;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.systigmaster.contable.servicios.interfaces.IDocumentosServ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Api(tags = {"Documentos"})
public class DocumentosCtrl {

    private final IDocumentosServ iDocumentosServ;

    public DocumentosCtrl(IDocumentosServ iDocumentosServ) {

        this.iDocumentosServ = iDocumentosServ;
    }

    @GetMapping("/api/cont/facturas")
    @ApiOperation(value = "Muestra las facturas de una empresa")
    public ResponseEntity<ResultadoTransaccion> getListaFacturas(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.FACTURA);
    }

    @GetMapping("/api/cont/notadebito")
    @ApiOperation(value = "Muestra las Notas de debito de una empresa")
    public ResponseEntity<ResultadoTransaccion> getListaNotaDebito(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.NOTA_DEBITO);
    }

    @GetMapping("/api/cont/notacredito")
    @ApiOperation(value = "Muestra las notas de credito de una empresa")
    public ResponseEntity<ResultadoTransaccion> getListaNotaCredito(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.NOTA_CREDITO);
    }

    @GetMapping("/api/cont/notapedido")
    @ApiOperation(value = "Muestra las notas de pedido de una empresa")
    public ResponseEntity<ResultadoTransaccion> getListaNotapedido(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.NOTA_PEDIDO);
    }

    @GetMapping("/api/cont/notaentrega")
    @ApiOperation(value = "Muestra las notas de entrega de una empresa")
    public ResponseEntity<ResultadoTransaccion> getListaNotaEntrega(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.NOTA_ENTREGA);
    }

    @GetMapping("/api/cont/notarecibo")
    @ApiOperation(value = "Muestra las Notas de recibido de una empresa")
    public ResponseEntity<ResultadoTransaccion> getListaNotaRecibo(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.NOTA_RECIBO);
    }

    @GetMapping("/api/cont/documento/{id_documento}")
    @ApiOperation(value = "Devuelve un documento por su id")
    public ResponseEntity<ResultadoTransaccion> getDocumento(@RequestHeader HttpHeaders headers, HttpSession session,
                                          @PathVariable Long id_documento) {
        return this.iDocumentosServ.getDocumento(headers, session, id_documento);
    }

    @GetMapping("/api/prv/info")
    @ApiOperation(value = "Servicio de prueba")
    public ResponseEntity<?> info() {
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
