package com.systig.systigmaster.inventario.repositorios.daos;

import com.systig.systigmaster.inventario.modelos.Propietario;
import com.systig.systigmaster.inventario.repositorios.interfaces.CrudPropietarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import static com.systig.systigmaster.inventario.utilidades.Utilidades.QUERIES_ORACLE.QUERY.SQL_PROPIETARIO;

@Repository
public class PropietarioDao implements CrudPropietarioRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PropietarioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Propietario get(Long id_item) {
        return this.jdbcTemplate.queryForObject(SQL_PROPIETARIO.getSQL(), new Object[]{id_item}, (rs, rwNumber) -> {
            Propietario propietario = new Propietario();
            propietario.setId(rs.getLong("id"));
            propietario.setTipoIdentificacion(rs.getString("tipo_identificacion"));
            propietario.setNroIdentificacion(rs.getString("nro_identificacion"));
            propietario.setNombres(rs.getString("nombres"));
            propietario.setApellidos(rs.getString("apellidos"));
            propietario.setRazonSocial(rs.getString("razon_social"));
            propietario.setTelefonoLocal(rs.getString("telef_local"));
            propietario.setTelefonoMovil(rs.getString("telef_movil"));
            propietario.setEmail(rs.getString("email"));
            propietario.setDireccionFiscal(rs.getString("direccion_fiscal"));
            propietario.setCodigoPostal(rs.getString("codigo_postal"));
            propietario.setProvincia(rs.getString("provincia"));
            propietario.setPais(rs.getString("pais"));
            return propietario;
        });
    }
}
