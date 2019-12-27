package com.systig.systigmaster.sesiones.servicios.implementaciones;

import com.google.gson.Gson;
import com.systig.base.repositorios.sesiones.entidades.Configuracion;
import com.systig.base.repositorios.sesiones.entidades.FormatoDocumento;
import com.systig.base.repositorios.sesiones.entidades.ConfiguracionDetalle;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionDefaultService {

    public static Configuracion getConfiguracion(Long idPropietario){
            return crearDefault(idPropietario);
    }

    private static Configuracion crearDefault(Long idPropietario){
        Configuracion configuracionDefault = new Configuracion();
        LocalDateTime fechaActual = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(fechaActual, ZoneId.systemDefault());

        ConfiguracionDetalle configuracion = new ConfiguracionDetalle();
        configuracion.setFechaRegistro(zdt.toInstant().toEpochMilli());
        configuracion.setFormatoDocumento(getListaFormatosPorDefecto());
        configuracion.setIsRetentor(false);
        configuracion.setNumeroTerminales(1L);

        configuracionDefault.setIdPropietario(idPropietario);
        configuracionDefault.setUrlInventario("http://localhost:8090");
        configuracionDefault.setUrlContable("http://localhost:8093");
        configuracionDefault.setUrlClientes("http://localhost:8091");
        configuracionDefault.setUrlProveedores("http://localhost:8092");
        configuracionDefault.setUrlSesiones("http://localhost:8096");
        configuracionDefault.setUrlTablero("http://localhost:4201/inicio/");
        configuracionDefault.setJsonConfiguracion((new Gson()).toJson(configuracion));

         return  configuracionDefault;
    }

    private static List<FormatoDocumento> getListaFormatosPorDefecto(){
        List<FormatoDocumento> listado = new ArrayList<>();

        FormatoDocumento notaPedido = new FormatoDocumento();
        notaPedido.setDenominacionDocumento("NOTA DE PEDIDO");
        notaPedido.setNroControlBase(BigInteger.ZERO);
        notaPedido.setNroControlFin(BigInteger.valueOf(999999));
        notaPedido.setNroFormato(1L);
        notaPedido.setTipoDocumento(1L);
        listado.add(notaPedido);

        FormatoDocumento notaRecibido = new FormatoDocumento();
        notaRecibido.setDenominacionDocumento("NOTA DE RECIBIDO");
        notaRecibido.setNroControlBase(BigInteger.ZERO);
        notaRecibido.setNroControlFin(BigInteger.valueOf(999999));
        notaRecibido.setNroFormato(2L);
        notaRecibido.setTipoDocumento(2L);
        listado.add(notaRecibido);

        FormatoDocumento notaFactura = new FormatoDocumento();
        notaFactura.setDenominacionDocumento("FACTURA DE VENTA");
        notaFactura.setNroControlBase(BigInteger.ZERO);
        notaFactura.setNroControlFin(BigInteger.valueOf(999999));
        notaFactura.setNroFormato(3L);
        notaFactura.setTipoDocumento(3L);
        listado.add(notaFactura);

        FormatoDocumento notaEntrega = new FormatoDocumento();
        notaEntrega.setDenominacionDocumento("FACTURA DE VENTA");
        notaEntrega.setNroControlBase(BigInteger.ZERO);
        notaEntrega.setNroControlFin(BigInteger.valueOf(999999));
        notaEntrega.setNroFormato(4L);
        notaEntrega.setTipoDocumento(4L);
        listado.add(notaEntrega);

        FormatoDocumento notaCredito = new FormatoDocumento();
        notaCredito.setDenominacionDocumento("NOTA DE CREDITO");
        notaCredito.setNroControlBase(BigInteger.ZERO);
        notaCredito.setNroControlFin(BigInteger.valueOf(999999));
        notaCredito.setNroFormato(5L);
        notaCredito.setTipoDocumento(5L);
        listado.add(notaCredito);

        FormatoDocumento notaDebito = new FormatoDocumento();
        notaDebito.setDenominacionDocumento("NOTA DE DEBITO");
        notaDebito.setNroControlBase(BigInteger.ZERO);
        notaDebito.setNroControlFin(BigInteger.valueOf(999999));
        notaDebito.setNroFormato(6L);
        notaDebito.setTipoDocumento(6L);
        listado.add(notaDebito);

        return listado;
    }
}
