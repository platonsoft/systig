package com.systig.remesas.servicios;

import com.systig.remesas.modelos.Pais;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class LocalBitcoinServ {

    void findAllOfertas(Pais pais){
        RestTemplate restTemplate = new RestTemplate();
        String Url = "https://localbitcoins.com/sell-bitcoins-online/";
        ResponseEntity<String> response = restTemplate.getForEntity(Url + pais.getCodigo() + "/" + pais.getDescripcion() + "/.json", String.class);

        System.out.println("resultado: " + response.getBody());
    }
}
