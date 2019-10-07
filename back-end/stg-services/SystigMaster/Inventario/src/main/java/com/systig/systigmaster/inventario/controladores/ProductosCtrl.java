package com.systig.systigmaster.inventario.controladores;

import com.systig.systigmaster.inventario.modelos.Almacen;
import com.systig.systigmaster.inventario.modelos.Categoria;
import com.systig.systigmaster.inventario.modelos.Producto;
import com.systig.systigmaster.inventario.modelos.Proveedor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductosCtrl {

    @RequestMapping("/api/productos/todos")
    public List<Producto> getListaProductos(@RequestHeader HttpHeaders headers) {
        List<Producto> productos = new ArrayList<>();

        System.out.println(headers.get("Authorization"));
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setCodigo("000001");
        producto.setNombre("Producto de Prueba 1");
        producto.setDescripcion("Producto de Prueba 1");
        producto.setUnidad("UND");
        producto.setImpuesto(new BigDecimal(20));
        producto.setDescuento(new BigDecimal(20));
        producto.setCantidadMinima(new BigDecimal(5));
        producto.setCantidadExistencia(new BigDecimal(10));
        producto.setMonto(new BigDecimal(2500.25));
        producto.setAlmacen(new Almacen());
        producto.setCategoria(new Categoria());
        producto.setProveedor(new Proveedor());
        producto.setDisponible(true);
        producto.setHistorial(new ArrayList<>());

        productos.add(producto);
        productos.add(producto);

        return productos;
    }

    @RequestMapping("/api/login")
    public String login() {
        return "Hola, jesus";
    }
}
