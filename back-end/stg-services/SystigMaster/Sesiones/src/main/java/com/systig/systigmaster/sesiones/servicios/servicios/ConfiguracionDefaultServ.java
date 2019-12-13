package com.systig.systigmaster.sesiones.servicios.servicios;

import com.google.gson.Gson;
import com.systig.systigmaster.sesiones.repositorios.interfaces.IConfiguracionDao;
import com.systig.systigmaster.sesiones.repositorios.modelos.*;
import com.systig.systigmaster.sesiones.repositorios.modelos.configuracion.FormatoDocumento;
import com.systig.systigmaster.sesiones.repositorios.modelos.configuracion.ObjConfiguracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracionDefaultServ {

    @Autowired
    private IConfiguracionDao iConfiguracionDao;

    public ConfiguracionDefaultServ() {
    }

    public Configuracion getConfiguracion(Long idPropietario){
        Optional<Configuracion> config = iConfiguracionDao.findAll().stream()
                .filter(configuracion -> configuracion.getIdPropietario().equals(idPropietario))
                .findFirst();
        return config.orElseGet(() -> this.crearDefault(idPropietario));
    }

    private Configuracion crearDefault(Long idPropietario){
        Configuracion configuracionDefault = new Configuracion();
        LocalDateTime fechaActual = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(fechaActual, ZoneId.systemDefault());

        ObjConfiguracion configuracion = new ObjConfiguracion();
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
        configuracionDefault.setJsonConfiguracion((new Gson()).toJson(configuracion));

         return  this.iConfiguracionDao.save(configuracionDefault);
    }

    private List<FormatoDocumento> getListaFormatosPorDefecto(){
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
