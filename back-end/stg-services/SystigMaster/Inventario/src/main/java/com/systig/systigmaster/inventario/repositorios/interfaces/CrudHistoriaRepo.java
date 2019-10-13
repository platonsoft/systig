package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.modelos.Historia;

import java.util.List;

public interface CrudHistoriaRepo {
    List getListaHistoria(Long idProducto);
    Historia getHistoria(Long idProducto, Long idHistoria);
    Boolean agregarHistoria(Historia historia);
}
