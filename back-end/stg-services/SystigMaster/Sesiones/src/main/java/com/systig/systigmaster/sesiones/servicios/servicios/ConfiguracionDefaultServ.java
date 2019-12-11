package com.systig.systigmaster.sesiones.servicios.servicios;

import com.systig.systigmaster.sesiones.repositorios.interfaces.IConfiguracionDao;
import com.systig.systigmaster.sesiones.repositorios.modelos.*;
import com.systig.systigmaster.sesiones.repositorios.modelos.configuracion.FormatoDocumento;
import com.systig.systigmaster.sesiones.repositorios.modelos.configuracion.ObjConfiguracion;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConfiguracionDefaultServ {

    private final IConfiguracionDao iConfiguracionDao;
    Configuracion configuracionDefault = new Configuracion();
    List<Rol> rolDefault = new ArrayList<>();
    List<ProductoSystig> productosSystigDefault = new ArrayList<>();

    Propietario propietarioDefault = new Propietario();
    Usuario usuarioDefault = new Usuario();
    Privilegio privilegioDefault = new Privilegio();

    LocalDateTime fechaActual = LocalDateTime.now();
    ZonedDateTime zdt = ZonedDateTime.of(fechaActual, ZoneId.systemDefault());

    public ConfiguracionDefaultServ(IConfiguracionDao iConfiguracionDao) {
        this.iConfiguracionDao = iConfiguracionDao;

        ObjConfiguracion configuracion = new ObjConfiguracion();
        configuracion.setFechaRegistro(zdt.toInstant().toEpochMilli());
        configuracion.setFormatoDocumento(getListaFormatosPorDefecto());
        configuracion.setIsRetentor(false);
        configuracion.setNumeroTerminales(1L);

        this.configuracionDefault.setIdConfiguracion(0L);
        this.configuracionDefault.setIdPropietario(0L);
        this.configuracionDefault.setUrlInventario("http://localhost:8090");
        this.configuracionDefault.setUrlContable("http://localhost:8093");
        this.configuracionDefault.setUrlClientes("http://localhost:8091");
        this.configuracionDefault.setUrlProveedores("http://localhost:8092");
        this.configuracionDefault.setUrlSesiones("http://localhost:8096");
        this.configuracionDefault.setJsonConfiguracion("");
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

    public Configuracion getConfiguracionDefault(){
        return this.configuracionDefault;
    }

    private void setConfiguracionDefault(Configuracion configuracion){

    }
}
