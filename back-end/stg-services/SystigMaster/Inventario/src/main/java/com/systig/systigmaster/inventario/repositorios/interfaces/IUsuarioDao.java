package com.systig.systigmaster.inventario.repositorios.interfaces;

import com.google.gson.Gson;
import com.systig.systigmaster.inventario.repositorios.modelos.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario getByUsernameEquals(String username);

    default String retornoToken(Usuario usuario){
        return Jwts.builder()
                .setSubject((new Gson()).toJson(usuario))
                .setExpiration(new Date(System.currentTimeMillis()+60000))
                .signWith(SignatureAlgorithm.HS512, "Pl@tonSoft")
                .compact();
    }

    default Usuario retornoUsuario(String token){
        String userJson = Jwts.parser()
                .setSigningKey("Pl@tonSoft")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return (new Gson()).fromJson(userJson,Usuario.class);
    }

    default Usuario statusSession(HttpHeaders headers, HttpSession session){
        try{
            String token =  String.valueOf(headers.get("TokenSystig")).replace("[","").replace("]","");
            Usuario usuarioActivo = retornoUsuario(token);
            if(usuarioActivo.getSessionId().equals(session.getId())){
                return usuarioActivo;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    class QUERIES_ORACLE{
        public enum QUERY{
            PRODUCTO_SYSTIG_CODIGO(102),

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
