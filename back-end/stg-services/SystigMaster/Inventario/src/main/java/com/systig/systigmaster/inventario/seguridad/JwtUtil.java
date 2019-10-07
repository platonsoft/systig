package com.systig.systigmaster.inventario.seguridad;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class JwtUtil {

    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    static void addAuthentication(HttpServletResponse res, String username) {

        String token = Jwts.builder()
                .setSubject(username)

                // Vamos a asignar un tiempo de expiracion de 1 minuto
                .setExpiration(new Date(System.currentTimeMillis()+60000))

                // Hash con el que firmaremos la clave
                .signWith(SignatureAlgorithm.HS512, "Pl@tonSoft")
                .compact();

        //agregamos al encabezado el token
        res.addHeader("authorization", "Bearer " + token);
        res.setHeader("expires", (new Date(System.currentTimeMillis()+60000)).toString());
    }

    // Método para validar el token enviado por el cliente
    static Authentication getAuthentication(HttpServletRequest request) {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("authorization");

        // si hay un token presente, entonces lo validamos

        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey("Pl@tonSoft")
                    .parseClaimsJws(token.replace("Bearer", "")) //este metodo es el que valida
                    .getBody()
                    .getSubject();

            // Recordamos que para las demás peticiones que no sean /login
            // no requerimos una autenticacion por username/password
            // por este motivo podemos devolver un UsernamePasswordAuthenticationToken sin password
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) :
                    null;
        }
        return null;
    }
}
