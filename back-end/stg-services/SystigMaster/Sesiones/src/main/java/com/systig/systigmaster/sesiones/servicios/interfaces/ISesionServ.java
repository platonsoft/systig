package com.systig.systigmaster.sesiones.servicios.interfaces;

import com.systig.systigmaster.sesiones.repositorios.modelos.Configuracion;
import com.systig.systigmaster.sesiones.repositorios.modelos.Propietario;
import com.systig.systigmaster.sesiones.repositorios.modelos.configuracion.FormatoDocumento;
import com.systig.systigmaster.sesiones.repositorios.modelos.configuracion.ObjConfiguracion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface ISesionServ {
    ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session);
    ResponseEntity<?> addUserSystig(HttpServletRequest request, HttpHeaders headers, HttpSession session, Propietario propietario);
    ResponseEntity<?> restoreUserSystig(String email);
    void enviaCorreo(String titulo, String contenido, String destinatario) throws Exception;

    default ObjConfiguracion getConfiguracionDefault(){
        ObjConfiguracion defaultConfig = new ObjConfiguracion();

        defaultConfig.setIsRetentor(false);
        defaultConfig.setFechaRegistro( (new Date()).getTime());
        defaultConfig.setNumeroTerminales(1L);

        List<FormatoDocumento> documentos = new ArrayList<>();

        FormatoDocumento facturaVenta = new FormatoDocumento();
        facturaVenta.setTipoDocumento(1L);
        facturaVenta.setDenominacionDocumento("Factura de Venta");
        facturaVenta.setNroControlBase(BigInteger.valueOf(0L));
        facturaVenta.setNroControlFin(BigInteger.valueOf(100L));
        facturaVenta.setResolucionDian("La DIAN dice algo");
        facturaVenta.setNroFormato(0L);
        documentos.add(facturaVenta);

        FormatoDocumento notaCredito = new FormatoDocumento();
        notaCredito.setTipoDocumento(2L);
        notaCredito.setDenominacionDocumento("Nota de Credito");
        notaCredito.setNroControlBase(BigInteger.valueOf(0L));
        notaCredito.setNroControlFin(BigInteger.valueOf(100L));
        notaCredito.setResolucionDian("La DIAN dice algo");
        notaCredito.setNroFormato(0L);
        documentos.add(notaCredito);

        FormatoDocumento notaDebito = new FormatoDocumento();
        notaDebito.setTipoDocumento(3L);
        notaDebito.setDenominacionDocumento("Nota de Debito");
        notaDebito.setNroControlBase(BigInteger.valueOf(0L));
        notaDebito.setNroControlFin(BigInteger.valueOf(100L));
        notaDebito.setResolucionDian("La DIAN dice algo");
        notaDebito.setNroFormato(0L);
        documentos.add(notaDebito);

        FormatoDocumento notaPedido = new FormatoDocumento();
        notaPedido.setTipoDocumento(1L);
        notaPedido.setDenominacionDocumento("Nota de Pedido");
        notaPedido.setNroControlBase(BigInteger.valueOf(0L));
        notaPedido.setNroControlFin(BigInteger.valueOf(100L));
        notaPedido.setResolucionDian("La DIAN dice algo");
        notaPedido.setNroFormato(0L);
        documentos.add(notaPedido);

        FormatoDocumento notaEntrega = new FormatoDocumento();
        notaEntrega.setTipoDocumento(1L);
        notaEntrega.setDenominacionDocumento("Nota de Entrega");
        notaEntrega.setNroControlBase(BigInteger.valueOf(0L));
        notaEntrega.setNroControlFin(BigInteger.valueOf(100L));
        notaEntrega.setResolucionDian("La DIAN dice algo");
        notaEntrega.setNroFormato(0L);
        documentos.add(notaEntrega);

        FormatoDocumento notaRecibo = new FormatoDocumento();
        notaRecibo.setTipoDocumento(1L);
        notaRecibo.setDenominacionDocumento("Nota de Recibo");
        notaRecibo.setNroControlBase(BigInteger.valueOf(0L));
        notaRecibo.setNroControlFin(BigInteger.valueOf(100L));
        notaRecibo.setResolucionDian("La DIAN dice algo");
        notaRecibo.setNroFormato(0L);
        documentos.add(notaRecibo);
        defaultConfig.setFormatoDocumento(documentos);
        return defaultConfig;
    }
}
