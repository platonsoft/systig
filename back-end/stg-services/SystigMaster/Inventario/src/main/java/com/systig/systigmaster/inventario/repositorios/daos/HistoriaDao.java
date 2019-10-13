package com.systig.systigmaster.inventario.repositorios.daos;

import com.systig.systigmaster.inventario.modelos.Historia;
import com.systig.systigmaster.inventario.repositorios.interfaces.CrudHistoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.systig.systigmaster.inventario.utilidades.Utilidades.QUERIES_ORACLE.QUERY.*;

@Repository
public class HistoriaDao implements CrudHistoriaRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HistoriaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List getListaHistoria(Long idProducto) {
        return jdbcTemplate.query(
                SQL_LISTA_HISTORIAL.getSQL(),
                new Object[] {idProducto},
                (rs, rowNum) -> {
                    Historia historia = new Historia();
                    historia.setId(rs.getLong("id"));
                    historia.setDescripcion(rs.getString("descripcion"));
                    historia.setElemento(rs.getString("elemento"));
                    historia.setOperacion(rs.getString("operacion"));
                    historia.setFecha(rs.getLong("fecha"));
                    return historia;
                });
    }

    @Override
    public Historia getHistoria(Long idProducto, Long idHistoria) {
        return this.jdbcTemplate.queryForObject(SQL_HISTORIAL.getSQL(), new Object[]{idProducto, idHistoria}, (rs, rwNumber) -> {
            Historia historia = new Historia();
            historia.setId(rs.getLong("id"));
            historia.setDescripcion(rs.getString("descripcion"));
            historia.setElemento(rs.getString("elemento"));
            historia.setOperacion(rs.getString("operacion"));
            historia.setFecha(rs.getLong("fecha"));
            return historia;
        });
    }

    @Override
    public Boolean agregarHistoria(Historia historia) {
        try{
            this.jdbcTemplate.update(SQL_AGREGAR_HISTORIAL.getSQL(),
                    historia.getId(),
                    historia.getDescripcion(),
                    historia.getElemento(),
                    historia.getOperacion(),
                    historia.getFecha());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
