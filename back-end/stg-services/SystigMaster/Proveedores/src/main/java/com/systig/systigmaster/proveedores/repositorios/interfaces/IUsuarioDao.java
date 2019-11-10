package com.systig.systigmaster.proveedores.repositorios.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.systig.systigmaster.proveedores.repositorios.modelos.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.util.Date;

public class IUsuarioDao {
    public String retornoToken(Usuario usuario){
        return Jwts.builder()
                .setSubject((new Gson()).toJson(usuario))
                .setExpiration(new Date(System.currentTimeMillis()+600000))
                .signWith(SignatureAlgorithm.HS512, "Pl@tonSoft")
                .compact();
    }

    private Usuario retornoUsuario(String token) throws IOException {
        String userJson = Jwts.parser()
                .setSigningKey("Pl@tonSoft")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        System.out.println("Token----> " + userJson);
        return new ObjectMapper().readValue(userJson, Usuario.class);
    }

    public Usuario statusSession(HttpHeaders headers){
        try{
            String token =  String.valueOf(headers.get("tokensystig")).replace("[","").replace("]","");
            return retornoUsuario(token);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
