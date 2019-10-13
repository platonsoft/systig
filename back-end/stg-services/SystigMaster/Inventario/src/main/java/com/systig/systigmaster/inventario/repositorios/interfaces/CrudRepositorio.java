package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.modelos.User;
import com.systig.systigmaster.inventario.modelos.UsuarioActivo;

import java.util.List;

public interface CrudRepositorio<T,U> {
    List<T> listaLigera(UsuarioActivo usuario);
    List<T> listaConmpleta(UsuarioActivo usuario);
    T get(UsuarioActivo usuario, Long id_item);
    Boolean agregarNuevo(UsuarioActivo usuario, T item);
    Boolean actualizar(UsuarioActivo usuario, T item);
    Boolean borrar(UsuarioActivo usuario, Long id_item);
}
