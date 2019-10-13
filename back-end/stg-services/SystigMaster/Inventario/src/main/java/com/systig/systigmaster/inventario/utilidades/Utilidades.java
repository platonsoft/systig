package com.systig.systigmaster.inventario.utilidades;

import com.google.gson.Gson;
import com.systig.systigmaster.inventario.modelos.UsuarioActivo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class Utilidades {

    public static String retornoToken(UsuarioActivo usuario){
        return Jwts.builder()
                .setSubject((new Gson()).toJson(usuario))
                .setExpiration(new Date(System.currentTimeMillis()+60000))
                .signWith(SignatureAlgorithm.HS512, "Pl@tonSoft")
                .compact();
    }

    public static UsuarioActivo retornoUsuario(String token){

        String userJson = Jwts.parser()
                .setSigningKey("Pl@tonSoft")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return (new Gson()).fromJson(userJson,UsuarioActivo.class);
    }

    public static class QUERIES_ORACLE{
        public enum QUERY{
            PRODUCTO_SYSTIG_CODIGO(100),

            SQL_LISTA_PRODUCTOS("SELECT * FROM STG_FACT_PRODUCTOS p WHERE p.CLIENTE_ID=?"),
            SQL_PRODUCTOS("SELECT * FROM STG_FACT_PRODUCTOS WHERE ID=?"),
            SQL_AGREGAR_PRODUCTOS("SELECT * FROM STG_FACT_CATEGORIAS"),
            SQL_ACTUALIZAR_PRODUCTOS("SELECT * FROM STG_FACT_CATEGORIAS"),
            SQL_BORRAR_PRODUCTOS("DELETE FROM STG_FACT_PRODUCTOS WHERE ID = ?"),

            SQL_LISTA_CATEGORIAS("SELECT * FROM STG_FACT_CATEGORIAS WHERE USERNAME=?"),
            SQL_CATEGORIA("SELECT * FROM STG_FACT_CATEGORIAS WHERE ID=?"),
            SQL_AGREGAR_CATEGORIAS("SELECT * FROM STG_FACT_CATEGORIAS"),
            SQL_ACTUALIZAR_CATEGORIAS("SELECT * FROM STG_FACT_CATEGORIAS"),
            SQL_BORRAR_CATEGORIAS("DELETE FROM STG_FACT_CATEGORIAS WHERE ID = ?"),

            SQL_LISTA_ALMACEN("SELECT * FROM STG_FACT_ALMACEN a WHERE a.USERNAME=?"),
            SQL_ALMACEN("SELECT * FROM STG_FACT_ALMACEN a WHERE a.ID=?"),
            SQL_AGREGAR_ALMACEN("INSERT INTO STG_FACT_ALMACEN(ID,NOMBRE,DESCRIPCION,TIPO,UBICACION,USERNAME) VALUES (?,?,?,?,?,?)"),
            SQL_ACTUALIZAR_ALMACEN("UPDATE STG_FACT_ALMACEN SET NOMBRE = ?, DESCRIPCION = ?, TIPO = ?, UBICACION = ?, USERNAME = ? WHERE ID = ?"),
            SQL_BORRAR_ALMACEN("DELETE FROM STG_FACT_ALMACEN WHERE ID = ?"),

            SQL_LISTA_PROVEEDOR("SELECT * FROM STG_FACT_CATEGORIAS"),
            SQL_PROVEEDOR("SELECT * FROM STG_FACT_CATEGORIAS"),
            SQL_AGREGAR_PROVEEDOR("SELECT * FROM STG_FACT_CATEGORIAS"),
            SQL_ACTUALIZAR_PROVEEDOR("SELECT * FROM STG_FACT_CATEGORIAS"),
            SQL_BORRAR_PROVEEDOR("DELETE FROM STG_FACT_ALMACEN WHERE ID = ?"),

            SQL_PROPIETARIO("SELECT * FROM STG_FACT_CATEGORIAS"),

            SQL_LISTA_HISTORIAL("SELECT * FROM STG_FACT_CATEGORIAS"),
            SQL_HISTORIAL("SELECT * FROM STG_FACT_CATEGORIAS"),
            SQL_AGREGAR_HISTORIAL("SELECT * FROM STG_FACT_CATEGORIAS"),

            SQL_USUARIO("SELECT USERNAME, PASSWORD, ENABLED FROM STG_USUARIOS WHERE USERNAME = ?"),
            SQL_USUARIO_ROLE("SELECT USERNAME, ROLE FROM STG_USUARIOS_ROLES WHERE USERNAME = ?"),
            SQL_USUARIO_PERMISOS("SELECT R.USERNAME, R.ROLE, P.PRODUCTO_SYSTIG_CODIGO, P.NIVEL_ACCESO, U.ID_CLIENTE FROM STG_USUARIOS U, STG_USUARIOS_ROLES R, STG_USUARIOS_PERMISOS P WHERE U.USERNAME=R.USERNAME AND R.USERNAME = P.USERNAME AND P.USERNAME=? AND P.PRODUCTO_SYSTIG_CODIGO=?");

            String retornoValor;
            Integer retornoValorEntero;

            QUERY(String cadenaSQL){
                this.retornoValor = cadenaSQL;
            }
            QUERY(Integer cadenaSQL){
                this.retornoValorEntero = cadenaSQL;
            }

            public String getSQL() {
                return this.retornoValor;
            }
            public Integer getNumeros() {
                return this.retornoValorEntero;
            }
        }
    }
}
