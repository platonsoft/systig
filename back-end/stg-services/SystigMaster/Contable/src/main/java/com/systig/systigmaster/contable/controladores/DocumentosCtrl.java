package com.systig.systigmaster.contable.controladores;

import com.systig.systigmaster.contable.repositorios.modelos.Documento;
import com.systig.systigmaster.contable.servicios.interfaces.IDocumentosServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.Principal;

@Controller
public class DocumentosCtrl {

    private final IDocumentosServ iDocumentosServ;

    public DocumentosCtrl(IDocumentosServ iDocumentosServ) {
        this.iDocumentosServ = iDocumentosServ;
    }

    @GetMapping("/api/login")
    public ResponseEntity<?> login(Principal principal, HttpServletRequest requests, HttpSession session) {
        return this.iDocumentosServ.getTokenSession(principal,requests,session);
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

    @PostMapping("/api/cont/documento")
    public ResponseEntity<?> addDocumento(@RequestHeader HttpHeaders headers, HttpSession session,
                                             @RequestBody Documento documento) {
        return this.iDocumentosServ.addDocumento(headers,session,documento);
    }

    @PutMapping("/api/cont/documento")
    public ResponseEntity<?> setDocumento(@RequestHeader HttpHeaders headers, HttpSession session,
                                                @RequestBody Documento documento) {
        return this.iDocumentosServ.setDocumento(headers,session,documento);
    }

    @DeleteMapping("/api/cont/documento")
    public ResponseEntity<?> chgDocumento(@RequestHeader HttpHeaders headers, HttpSession session,
                                            @RequestBody Documento documento) {
        return this.iDocumentosServ.chgDocumento(headers,session,documento);
    }

    @GetMapping("/api/cont/documento/historia/{id_documento}")
    public ResponseEntity<?> getHistoriaDocumento(@RequestHeader HttpHeaders headers, HttpSession session,
                                                 @PathVariable Long id_documento) {
        return this.iDocumentosServ.getHistoriaDocumentos(headers,session,id_documento);
    }

    @PostMapping(value = "/api/inv/producto/importar", consumes = {"multipart/form-data"})
    public ResponseEntity<?> importarArchivo(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) throws IOException {
        return new ResponseEntity<>(file.getName(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/inv/producto/exportar/XML", produces = { "application/xml", "text/xml" })
    public ResponseEntity exportarXML() {
        return null;
    }
}
