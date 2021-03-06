package com.systig.systigmaster.contable.servicios.interfaces;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.contable.entidades.Documento;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface IDocumentosServ {

    ResponseEntity<ResultadoTransaccion> getListaDocumentos(HttpHeaders headers, HttpSession session, TIPO_DOCUMENTO tipoDocumento);
    ResponseEntity<ResultadoTransaccion> getDocumento(HttpHeaders headers, HttpSession session, Long idDocumento);

    enum TIPO_DOCUMENTO{
        FACTURA(1),
        NOTA_CREDITO(2),
        NOTA_DEBITO(3),
        NOTA_PEDIDO(4),
        NOTA_ENTREGA(5),
        NOTA_RECIBO(6),
        OTRO_DOCUMENTO(7);

        Integer tipoDocumento;

        TIPO_DOCUMENTO(Integer tipoDocumento){
            this.tipoDocumento = tipoDocumento;
        }

        public Integer getTipoDocumento() {
            return this.tipoDocumento;
        }
    }

    enum ESTADO_DOCUMENTO{
        MODIFICABLE(1),
        TERMINADO(2),
        ANULADO(3),
        ENDIAN(4);

        Integer estadoDocumento;

        ESTADO_DOCUMENTO(Integer estadoDocumento){
            this.estadoDocumento = estadoDocumento;
        }

        public Integer getEstadoDocumento() {
            return this.estadoDocumento;
        }
    }
}
