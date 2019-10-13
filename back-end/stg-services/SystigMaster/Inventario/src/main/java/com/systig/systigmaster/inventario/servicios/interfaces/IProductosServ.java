package com.systig.systigmaster.inventario.servicios.interfaces;

import com.systig.systigmaster.inventario.modelos.Producto;
import com.systig.systigmaster.inventario.modelos.UsuarioActivo;

import java.util.List;

public interface IProductosServ {

    List getListadoLigero(UsuarioActivo usuarioActivo);
    Producto getProducto(UsuarioActivo usuarioActivo, Long idProducto);
    void nuevoProducto(UsuarioActivo usuarioActivo, Producto producto);
    void actualizarProducto(UsuarioActivo usuarioActivo, Producto producto);
    void borrarProducto(UsuarioActivo usuarioActivo, Long idProducto);
    List getHistoriaProducto(Long idProducto);

}
