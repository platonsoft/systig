package com.systig.systigmaster.inventario.repositorios.daos;

import com.systig.systigmaster.inventario.modelos.Proveedor;
import com.systig.systigmaster.inventario.modelos.UsuarioActivo;
import com.systig.systigmaster.inventario.repositorios.interfaces.CrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.systig.systigmaster.inventario.utilidades.Utilidades.QUERIES_ORACLE.QUERY.*;

@Repository
public class ProveedorDao implements CrudRepositorio<Proveedor,Long> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProveedorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Proveedor> listaLigera(UsuarioActivo usuario) {
        return this.jdbcTemplate.query(
                SQL_LISTA_PROVEEDOR.getSQL(),
                new Object[] {usuario.getUsername()},
                (rs, rowNum) -> {
                    Proveedor proveedor = new Proveedor();
                    proveedor.setId(rs.getLong("id"));
                    proveedor.setTipoDocumento(rs.getLong("tipo_doc"));
                    proveedor.setNumeroDocumento(rs.getString("nro_doc"));
                    proveedor.setRazonSocial(rs.getString("razon_social"));
                    proveedor.setTelefonoLocal(rs.getString("telef_local"));
                    proveedor.setTelefonoMovil(rs.getString("telef_movil"));
                    proveedor.setEmail(rs.getString("email"));
                    proveedor.setWeb(rs.getString("web"));
                    proveedor.setDireccionFiscal(rs.getString("direccion_fiscal"));
                    proveedor.setCodigoPostal(rs.getString("codigo_postal"));
                    proveedor.setProvincia(rs.getString("provincia"));
                    proveedor.setPais(rs.getString("pais"));
                    return proveedor;
                });
    }

    @Override
    public List<Proveedor> listaConmpleta(UsuarioActivo usuario) {
        return null;
    }

    @Override
    public Proveedor get(UsuarioActivo usuario, Long id_item) {
        return this.jdbcTemplate.queryForObject(SQL_PROVEEDOR.getSQL(), new Object[]{id_item, usuario.getUsername()}, (rs, rwNumber) -> {
            Proveedor proveedor = new Proveedor();
            proveedor.setId(rs.getLong("id"));
            proveedor.setTipoDocumento(rs.getLong("tipo_doc"));
            proveedor.setNumeroDocumento(rs.getString("nro_doc"));
            proveedor.setRazonSocial(rs.getString("razon_social"));
            proveedor.setTelefonoLocal(rs.getString("telef_local"));
            proveedor.setTelefonoMovil(rs.getString("telef_movil"));
            proveedor.setEmail(rs.getString("email"));
            proveedor.setWeb(rs.getString("web"));
            proveedor.setDireccionFiscal(rs.getString("direccion_fiscal"));
            proveedor.setCodigoPostal(rs.getString("codigo_postal"));
            proveedor.setProvincia(rs.getString("provincia"));
            proveedor.setPais(rs.getString("pais"));
            return proveedor;
        });
    }

    @Override
    public void agregarNuevo(UsuarioActivo usuario, Proveedor item) {
        this.jdbcTemplate.update(SQL_AGREGAR_PROVEEDOR.getSQL(),
                item.getId(),
                item.getTipoDocumento(),
                item.getNumeroDocumento(),
                item.getRazonSocial(),
                item.getTelefonoLocal(),
                item.getTelefonoMovil(),
                item.getEmail(),
                item.getWeb(),
                item.getDireccionFiscal(),
                item.getCodigoPostal(),
                item.getProvincia(),
                item.getPais(),
                usuario.getUsername());
    }

    @Override
    public void actualizar(UsuarioActivo usuario, Proveedor item) {
        this.jdbcTemplate.update(SQL_ACTUALIZAR_PROVEEDOR.getSQL(),
                item.getId(),
                item.getTipoDocumento(),
                item.getNumeroDocumento(),
                item.getRazonSocial(),
                item.getTelefonoLocal(),
                item.getTelefonoMovil(),
                item.getEmail(),
                item.getWeb(),
                item.getDireccionFiscal(),
                item.getCodigoPostal(),
                item.getProvincia(),
                item.getPais(),
                usuario.getUsername());
    }

    @Override
    public Boolean borrar(UsuarioActivo usuario, Long id_item) {
        try{
            this.jdbcTemplate.update(SQL_BORRAR_PROVEEDOR.getSQL(), id_item, usuario.getUsername());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
