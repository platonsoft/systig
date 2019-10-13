package com.systig.systigmaster.inventario.servicios.servicios;

import com.systig.systigmaster.inventario.modelos.Producto;
import com.systig.systigmaster.inventario.modelos.UsuarioActivo;
import com.systig.systigmaster.inventario.repositorios.interfaces.CrudHistoriaRepo;
import com.systig.systigmaster.inventario.repositorios.interfaces.CrudRepositorio;
import com.systig.systigmaster.inventario.servicios.interfaces.IProductosServ;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServ implements IProductosServ {

    private final CrudRepositorio repositorio;
    private final CrudHistoriaRepo historial;


    public ProductoServ(@Qualifier("productoDao") CrudRepositorio repositorio, CrudHistoriaRepo historial) {
        this.repositorio = repositorio;
        this.historial = historial;
    }

    @Override
    public List getListadoLigero(UsuarioActivo usuarioActivo) {
        return this.repositorio.listaLigera(usuarioActivo);
    }

    @Override
    public Producto getProducto(UsuarioActivo usuarioActivo, Long idProducto) {
        return (Producto) this.repositorio.get(usuarioActivo, idProducto);
    }

    @Override
    public void nuevoProducto(UsuarioActivo usuarioActivo, Producto producto) {
        repositorio.agregarNuevo(usuarioActivo, producto);
    }

    @Override
    public void actualizarProducto(UsuarioActivo usuarioActivo, Producto producto) {
        repositorio.actualizar(usuarioActivo, producto);
    }

    @Override
    public void borrarProducto(UsuarioActivo usuarioActivo, Long idProducto) {
        repositorio.borrar(usuarioActivo, idProducto);
    }

    @Override
    public List getHistoriaProducto(Long idProducto) {
        return historial.getListaHistoria(idProducto);
    }

}
