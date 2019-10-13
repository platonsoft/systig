package com.systig.systigmaster.inventario.repositorios.daos;

import com.systig.systigmaster.inventario.modelos.*;
import com.systig.systigmaster.inventario.repositorios.interfaces.CrudRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.systig.systigmaster.inventario.utilidades.Utilidades.QUERIES_ORACLE.QUERY.*;

@Repository
public class ProductoDao implements CrudRepositorio<Producto,Long> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Producto> listaLigera(UsuarioActivo usuario) {
        return jdbcTemplate.query(
                SQL_LISTA_PRODUCTOS.getSQL(),
                new Object[] {usuario.getPropietario().getId()},
                (rs, rowNum) -> {
                    Producto producto = new Producto();
                    producto.setId(rs.getLong("id"));
                    producto.setCodigo(rs.getString("codigo"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setUnidad(rs.getString("unidad"));
                    producto.setImpuesto(rs.getBigDecimal("impuesto"));
                    producto.setDescuento(rs.getBigDecimal("descuento"));
                    producto.setCantidadMinima(rs.getBigDecimal("cantidad_minima"));
                    producto.setCantidadOptima(rs.getBigDecimal("cantidad_optima"));
                    producto.setCantidadExistencia(rs.getBigDecimal("cantidad_existencia"));
                    producto.setMonto(rs.getBigDecimal("monto"));

                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getLong("categoria"));
                    producto.setCategoria(categoria);

                    Almacen almacen = new Almacen();
                    almacen.setId(rs.getLong("almacen"));
                    producto.setAlmacen(almacen);

                    Proveedor proveedor = new Proveedor();
                    proveedor.setId(rs.getLong("proveedor"));
                    producto.setProveedor(proveedor);

                    Propietario propietario = new Propietario();
                    propietario.setId(rs.getLong("cliente_id"));
                    producto.setPropietario(propietario);

                    producto.setDisponible(rs.getBoolean("disponible"));
                    return producto;
                });
    }

    @Override
    public List<Producto> listaConmpleta(UsuarioActivo usuario) {
        return null;
    }

    @Override
    public Producto get(UsuarioActivo usuario, Long id_item) {
        return this.jdbcTemplate.queryForObject(SQL_PRODUCTOS.getSQL(), new Object[]{id_item, usuario.getUsername()}, (rs, rwNumber) -> {
            Producto producto = new Producto();
            producto.setId(rs.getLong("id"));
            producto.setCodigo(rs.getString("codigo"));
            producto.setNombre(rs.getString("nombre"));
            producto.setDescripcion(rs.getString("descripcion"));
            producto.setUnidad(rs.getString("unidad"));
            producto.setImpuesto(rs.getBigDecimal("impuesto"));
            producto.setDescuento(rs.getBigDecimal("descuento"));
            producto.setCantidadMinima(rs.getBigDecimal("cantidadMinima"));
            producto.setCantidadExistencia(rs.getBigDecimal("cantidadExistencia"));
            producto.setMonto(rs.getBigDecimal("monto"));

            Categoria categoria = new Categoria();
            categoria.setId(rs.getLong("categoria"));
            producto.setCategoria(categoria);

            Almacen almacen = new Almacen();
            almacen.setId(rs.getLong("almacen"));
            producto.setAlmacen(almacen);

            Proveedor proveedor = new Proveedor();
            proveedor.setId(rs.getLong("proveedor"));
            producto.setProveedor(proveedor);

            Propietario propietario = new Propietario();
            propietario.setId(rs.getLong("cliente_id"));
            producto.setPropietario(propietario);

            producto.setDisponible(rs.getBoolean("disponible"));
            return producto;
        });
    }

    @Override
    public Boolean agregarNuevo(UsuarioActivo usuario, Producto item) {
        try{
            this.jdbcTemplate.update(SQL_AGREGAR_PRODUCTOS.getSQL(),
                    item.getId(),
                    item.getCodigo(),
                    item.getNombre(),
                    item.getDescripcion(),
                    item.getUnidad(),
                    item.getImpuesto(),
                    item.getDescuento(),
                    item.getCantidadMinima(),
                    item.getCantidadExistencia(),
                    item.getMonto(),
                    item.getCategoria()!=null?item.getCategoria().getId():0,
                    item.getProveedor().getId()!=null?item.getProveedor().getId():0,
                    item.getPropietario().getId()!=null?item.getPropietario().getId():0,
                    item.getDisponible(),
                    usuario.getUsername());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean actualizar(UsuarioActivo usuario, Producto item) {
        try {
            this.jdbcTemplate.update(SQL_ACTUALIZAR_PRODUCTOS.getSQL(),
                    item.getId(),
                    item.getCodigo(),
                    item.getNombre(),
                    item.getDescripcion(),
                    item.getUnidad(),
                    item.getImpuesto(),
                    item.getDescuento(),
                    item.getCantidadMinima(),
                    item.getCantidadExistencia(),
                    item.getMonto(),
                    item.getCategoria()!=null?item.getCategoria().getId():0,
                    item.getProveedor().getId()!=null?item.getProveedor().getId():0,
                    item.getPropietario().getId()!=null?item.getPropietario().getId():0,
                    item.getDisponible(),
                    usuario.getUsername());
            return true;
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public Boolean borrar(UsuarioActivo usuario, Long id_item) {
        try{
            this.jdbcTemplate.update(SQL_BORRAR_PRODUCTOS.getSQL(), id_item, usuario.getUsername());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
