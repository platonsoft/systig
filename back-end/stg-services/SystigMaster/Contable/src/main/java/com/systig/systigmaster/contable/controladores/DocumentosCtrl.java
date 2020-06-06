package com.systig.systigmaster.contable.controladores;

import com.systig.systigmaster.contable.servicios.interfaces.IDocumentosServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpSession;

@Controller
public class DocumentosCtrl {

    private final IDocumentosServ iDocumentosServ;

    public DocumentosCtrl(IDocumentosServ iDocumentosServ) {

        this.iDocumentosServ = iDocumentosServ;
    }

    @GetMapping("/api/cont/facturas")
    public ResponseEntity<?> getListaFacturas(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.FACTURA);
    }

    @GetMapping("/api/cont/notadebito")
    public ResponseEntity<?> getListaNotaDebito(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.NOTA_DEBITO);
    }

    @GetMapping("/api/cont/notacredito")
    public ResponseEntity<?> getListaNotaCredito(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.NOTA_CREDITO);
    }

    @GetMapping("/api/cont/notapedido")
    public ResponseEntity<?> getListaNotapedido(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.NOTA_PEDIDO);
    }

    @GetMapping("/api/cont/notaentrega")
    public ResponseEntity<?> getListaNotaEntrega(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.NOTA_ENTREGA);
    }

    @GetMapping("/api/cont/notarecibo")
    public ResponseEntity<?> getListaNotaRecibo(@RequestHeader HttpHeaders headers, HttpSession session) {
        return this.iDocumentosServ.getListaDocumentos(headers,session, IDocumentosServ.TIPO_DOCUMENTO.NOTA_RECIBO);
    }

    @GetMapping("/api/cont/documento/{id_documento}")
    public ResponseEntity<?> getDocumento(@RequestHeader HttpHeaders headers, HttpSession session,
                                          @PathVariable Long id_documento) {
        return this.iDocumentosServ.getDocumento(headers, session, id_documento);
    }
}
