package com.systig.systigmaster.inventario.repositorios.daos;

import com.systig.systigmaster.inventario.modelos.Almacen;
import com.systig.systigmaster.inventario.modelos.UsuarioActivo;
import com.systig.systigmaster.inventario.repositorios.interfaces.CrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.systig.systigmaster.inventario.utilidades.Utilidades.QUERIES_ORACLE.QUERY.*;

@Repository
public class AlmacenDao implements CrudRepositorio<Almacen, Long> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AlmacenDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Almacen> listaLigera(UsuarioActivo usuario) {
        return this.jdbcTemplate.query(
                SQL_LISTA_ALMACEN.getSQL(),
                new Object[] {usuario.getUsername()},
                (rs, rowNum) -> {
                    Almacen almacen = new Almacen();
                    almacen.setId(rs.getLong("id"));
                    almacen.setNombre(rs.getString("nombre"));
                    almacen.setDescripcion(rs.getString("descripcion"));
                    almacen.setTipo(rs.getString("tipo"));
                    almacen.setUbicacion(rs.getString("ubicacion"));
                    almacen.setUsername(rs.getString("username"));
                    return almacen;
                });
    }

    @Override
    public List<Almacen> listaConmpleta(UsuarioActivo usuario) {
        return null;
    }

    @Override
    public Almacen get(UsuarioActivo usuario, Long id_item) {
        return this.jdbcTemplate.queryForObject(SQL_ALMACEN.getSQL(), new Object[]{id_item},
                (rs, rwNumber) -> {
            Almacen almacen = new Almacen();
            almacen.setId(rs.getLong("id"));
            almacen.setNombre(rs.getString("nombre"));
            almacen.setDescripcion(rs.getString("descripcion"));
            almacen.setTipo(rs.getString("tipo"));
            almacen.setUbicacion(rs.getString("ubicacion"));
            almacen.setUsername(rs.getString("username"));
            return almacen;
        });
    }

    @Override
    public Boolean agregarNuevo(UsuarioActivo usuario, Almacen item) {
        try {
            this.jdbcTemplate.update(SQL_AGREGAR_ALMACEN.getSQL(),
                    item.getId(),
                    item.getNombre(),
                    item.getDescripcion(),
                    item.getTipo(),
                    item.getUbicacion(),
                    item.getUsername(),
                    usuario.getUsername());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean actualizar(UsuarioActivo usuario, Almacen item) {
        try {
            this.jdbcTemplate.update(SQL_ACTUALIZAR_ALMACEN.getSQL(),
                    item.getNombre(),
                    item.getDescripcion(),
                    item.getTipo(),
                    item.getUbicacion(),
                    usuario.getUsername(),
                    item.getId());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean borrar(UsuarioActivo usuario, Long id_item) {
        try{
            this.jdbcTemplate.update(SQL_BORRAR_ALMACEN.getSQL(), id_item, usuario.getUsername());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
