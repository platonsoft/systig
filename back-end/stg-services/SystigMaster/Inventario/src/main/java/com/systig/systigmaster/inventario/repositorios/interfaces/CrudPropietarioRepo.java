package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.systig.systigmaster.inventario.modelos.Propietario;

public interface CrudPropietarioRepo {
    Propietario get(Long id_item);
}
