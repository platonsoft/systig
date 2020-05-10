package com.systig.systigmaster.sesiones.utilidades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.systig.base.repositorios.nominas.entidades.Pais;
import com.systig.base.repositorios.pay.entidades.Tasa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class Pruebas {
    public static void main(String[] args) throws JsonProcessingException {
        // Object res = listaTasas();

        int numero = 0; // Numero a buscar su consecutivo
        int[] V = {3,0,0,1,0,1,3,2,0,0,0,1,2}; // Vector de muestreo

        for (int i = 0; i < V.length; i++) { // recorrido del vector
            if (V[i] == numero){ // comparar el valor centro
                if (V[i] == V[i+1]){ // verificar que el valor de la derecha sea igual al del centro
                    System.out.print(numero); // imprime el valor buscado
                }
            }
        }
    }

    public static List<Tasa> listaTasas(){
        try {
            List<Tasa> listadoTasas = new ArrayList<>();
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper monedas = new ObjectMapper();
            String Url = "https://localbitcoins.com/bitcoinaverage/ticker-all-currencies/";
            ResponseEntity<String> response = restTemplate.getForEntity(Url, String.class);
            monedas.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            Map<String, Tasa> cuerpo = monedas.readValue(response.getBody(), new TypeReference<Map<String,Tasa>>(){});

            System.out.println("resultado: " + response.getStatusCodeValue());
            for (String moneda : cuerpo.keySet()) {
                Tasa miTasa = cuerpo.get(moneda);
                miTasa.setCodMoneda(moneda);
                listadoTasas.add(miTasa);
            }
            System.out.println( "Tasas Cargadas: " +listadoTasas.size());
            return listadoTasas;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
