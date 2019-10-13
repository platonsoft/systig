package com.systig.systigmaster.inventario.repositorios.daos;

import com.systig.systigmaster.inventario.modelos.Categoria;
import com.systig.systigmaster.inventario.modelos.UsuarioActivo;
import com.systig.systigmaster.inventario.repositorios.interfaces.CrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.systig.systigmaster.inventario.utilidades.Utilidades.QUERIES_ORACLE.QUERY.*;

@Repository
public class CategoriaDao implements CrudRepositorio<Categoria, Long> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoriaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Categoria> listaLigera(UsuarioActivo usuario) {
        return this.jdbcTemplate.query(
                SQL_LISTA_CATEGORIAS.getSQL(),
                new Object[] {usuario.getUsername()},
                (rs, rowNum) -> {
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getLong("id"));
                    categoria.setNombre(rs.getString("nombre"));
                    categoria.setDescripcion(rs.getString("descripcion"));

                    Categoria categoriaPadre = new Categoria();
                    categoriaPadre.setId(rs.getLong("padre_id"));
                    categoria.setPadre(categoriaPadre);
                    categoria.setUsername(rs.getString("username"));
                    return categoria;
                });
    }

    @Override
    public List<Categoria> listaConmpleta(UsuarioActivo usuario) {
        return null;
    }

    @Override
    public Categoria get(UsuarioActivo usuario, Long id_item) {
        return this.jdbcTemplate.queryForObject(SQL_CATEGORIA.getSQL(), new Object[]{id_item, usuario.getUsername()}, (rs, rwNumber) -> {
            Categoria categoria = new Categoria();
            categoria.setId(rs.getLong("id"));
            categoria.setNombre(rs.getString("nombre"));
            categoria.setDescripcion(rs.getString("descripcion"));

            Categoria categoriaPadre = new Categoria();
            categoriaPadre.setId(rs.getLong("padre_id"));
            categoria.setPadre(categoriaPadre);

            categoria.setUsername(rs.getString("username"));
            return categoria;
        });
    }

    @Override
    public void agregarNuevo(UsuarioActivo usuario, Categoria item) {
        this.jdbcTemplate.update(SQL_AGREGAR_CATEGORIAS.getSQL(), item.getId(),
                item.getNombre(),
                item.getDescripcion(),
                item.getPadre().getId(),
                item.getUsername());
    }

    @Override
    public void actualizar(UsuarioActivo usuario, Categoria item) {
        this.jdbcTemplate.update(SQL_ACTUALIZAR_CATEGORIAS.getSQL(), item.getId(),
                item.getNombre(),
                item.getDescripcion(),
                item.getPadre().getId(),
                item.getUsername(),
                usuario.getUsername());
    }

    @Override
    public Boolean borrar(UsuarioActivo usuario, Long id_item) {
        try{
            this.jdbcTemplate.update(SQL_BORRAR_CATEGORIAS.getSQL(), id_item, usuario.getUsername());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
