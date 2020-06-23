package com.systig.systigmaster.pagos.servicios;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.systig.base.objetos.Oferta;
import com.systig.base.objetos.ResultadoTransaccion;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface IPagosServ {
    ResponseEntity<ResultadoTransaccion> getListaTransacciones(HttpHeaders headers);
    ResponseEntity<ResultadoTransaccion> getListaNotificaciones(HttpHeaders headers);
    ResponseEntity<ResultadoTransaccion> getListaBancos(HttpHeaders headers);
    ResponseEntity<ResultadoTransaccion> getTransaccion(Long idTransaccion, HttpHeaders headers);
    ResponseEntity<ResultadoTransaccion> getTasas(HttpHeaders headers);
    ResponseEntity<ResultadoTransaccion> getSaldo(HttpHeaders headers);
    ResponseEntity<ResultadoTransaccion> addTransaccion(String transaccion, HttpHeaders headers);
    ResponseEntity<ResultadoTransaccion> confirmarTransaccion(String refOperacion, BigDecimal montoConfirmado, HttpHeaders headers);

    default String genNroReferencia(){
        String resultado = "";
        for (int i = 1; i <= 8; i++) {
            resultado = resultado + (int) (Math.random() * 60 + 1);
        }
        return resultado;
    }

    default Oferta CalculoPrecioCompra(BigDecimal montoAccion, String bancoAccion, String codMoneda){
        String UrlCompra = "https://localbitcoins.com/buy-bitcoins-online/" + codMoneda + "/.json";

        try {
            List<Oferta> ofertaList = new ArrayList<>();

            while (ofertaList.size()<=0){
                RestTemplate restTemplate = new RestTemplate();
                ObjectMapper sellData = new ObjectMapper();
                ResponseEntity<String> response = restTemplate.getForEntity(UrlCompra, String.class);
                sellData.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                JSONObject json = new JSONObject(response.getBody());

                System.out.println("resultado Status: " + response.getStatusCodeValue());

                for (int i = 0; i <json.getJSONObject("data").getJSONArray("ad_list").length(); i++) {
                    JSONObject oferta = (JSONObject) json.getJSONObject("data").getJSONArray("ad_list").get(i);
                    Oferta ofertaTemp = new Oferta();

                    try {
                        ofertaTemp.setMontoMin(new BigDecimal(Objects.requireNonNull(oferta.getJSONObject("data").getString("min_amount"))));
                    }catch (Exception ex){
                        ofertaTemp.setMontoMin(BigDecimal.ONE);
                    }

                    try {
                        ofertaTemp.setMontoMax(new BigDecimal(oferta.getJSONObject("data").getString("max_amount")));
                    }catch (Exception ex){
                        try {
                            ofertaTemp.setMontoMax(new BigDecimal(oferta.getJSONObject("data").getString("max_amount_available")));
                        }catch ( Exception e){
                            ofertaTemp.setMontoMax(BigDecimal.ZERO);
                        }
                    }
                    ofertaTemp.setPrecioBTC(new BigDecimal(Objects.requireNonNull(oferta.getJSONObject("data").getString("temp_price"))));
                    ofertaTemp.setPrecioUSD(new BigDecimal(Objects.requireNonNull(oferta.getJSONObject("data").getString("temp_price_usd"))));
                    ofertaTemp.setNombreBanco(oferta.getJSONObject("data").getString("bank_name"));
                    ofertaTemp.setLinkAcceso(oferta.getJSONObject("actions").getString("public_view"));
                    ofertaTemp.setMoneda(oferta.getJSONObject("data").getString("currency"));
                    ofertaTemp.setPerfilScore(new BigDecimal(Objects.requireNonNull(oferta.getJSONObject("data").getJSONObject("profile").getString("feedback_score"))));
                    ofertaTemp.setPerfilNroTransacciones(new BigDecimal(Objects.requireNonNull(oferta.getJSONObject("data").getJSONObject("profile").getString("trade_count")).replace("+","").replace(" ","")));
                    ofertaTemp.setMontoAccion(montoAccion);

                    if (ofertaTemp.getMontoAccion().compareTo(ofertaTemp.getMontoMin())>=0
                            && ofertaTemp.getMontoMax().compareTo(ofertaTemp.getMontoAccion())>=0
                            && ofertaTemp.getNombreBanco().toLowerCase().contains(bancoAccion.toLowerCase())){
                        // Nivel 1
                        if (ofertaTemp.getPerfilNroTransacciones().compareTo(new BigDecimal(50L))>=0
                                && ofertaTemp.getPerfilNroTransacciones().compareTo(new BigDecimal(500L))<=0){

                            if (ofertaTemp.getPerfilScore().compareTo(new BigDecimal(100L))>=0){
                                ofertaList.add(ofertaTemp);
                            }

                        }else if (ofertaTemp.getPerfilNroTransacciones().compareTo(new BigDecimal(501L))>=0 ){  // Nivel 2

                            if (ofertaTemp.getPerfilScore().compareTo(new BigDecimal(98L))>=0){
                                ofertaList.add(ofertaTemp);
                            }

                        }
                    }
                }

                ofertaList.sort((o1, o2) -> {
                    if (o1.getPrecioBTC().compareTo(o2.getPrecioBTC())==0){
                        return 0;
                    }else if (o1.getPrecioBTC().compareTo(o2.getPrecioBTC())>0){
                        return 1;
                    }else {
                        return -1;
                    }
                });

                if (json.getJSONObject("pagination").has("next")){
                    UrlCompra = json.getJSONObject("pagination").getString("next");
                }else{
                    break;
                }

                if (ofertaList.size()<=0){
                    System.out.println("No hay Resultados de compra");
                }else {
                    return ofertaList.get(0);
                }
            }
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    default Oferta CalculoPrecioVenta(BigDecimal montoAccion, String bancoAccion, String codMoneda){
        String UrlVenta = "https://localbitcoins.com/sell-bitcoins-online/" + codMoneda + "/.json";
        try {
            List<Oferta> ofertaList = new ArrayList<>();

            while (ofertaList.size()<=0){
                RestTemplate restTemplate = new RestTemplate();
                ObjectMapper sellData = new ObjectMapper();

                ResponseEntity<String> response = restTemplate.getForEntity(UrlVenta, String.class);
                sellData.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                JSONObject json = new JSONObject(response.getBody());

                System.out.println("resultado Status: " + response.getStatusCodeValue());

                for (int i = 0; i <json.getJSONObject("data").getJSONArray("ad_list").length(); i++) {
                    JSONObject oferta = (JSONObject) json.getJSONObject("data").getJSONArray("ad_list").get(i);
                    Oferta ofertaTemp = new Oferta();

                    try {
                        ofertaTemp.setMontoMin(new BigDecimal(oferta.getJSONObject("data").getString("min_amount")));
                    }catch (Exception ex){
                        ofertaTemp.setMontoMin(BigDecimal.ONE);
                    }
                    try {
                        ofertaTemp.setMontoMax(new BigDecimal(oferta.getJSONObject("data").getString("max_amount")));
                    }catch (Exception ex){
                        try {
                            ofertaTemp.setMontoMax(new BigDecimal(oferta.getJSONObject("data").getString("max_amount_available")));
                        }catch (Exception e){
                            ofertaTemp.setMontoMax(BigDecimal.ZERO);
                        }
                    }
                    ofertaTemp.setPrecioBTC(new BigDecimal(oferta.getJSONObject("data").getString("temp_price")));
                    ofertaTemp.setPrecioUSD(new BigDecimal(oferta.getJSONObject("data").getString("temp_price_usd")));
                    ofertaTemp.setNombreBanco(oferta.getJSONObject("data").getString("bank_name"));
                    ofertaTemp.setLinkAcceso(oferta.getJSONObject("actions").getString("public_view"));
                    ofertaTemp.setMoneda(oferta.getJSONObject("data").getString("currency"));
                    ofertaTemp.setPerfilScore(new BigDecimal(oferta.getJSONObject("data").getJSONObject("profile").getString("feedback_score")));
                    ofertaTemp.setPerfilNroTransacciones(new BigDecimal(oferta.getJSONObject("data").getJSONObject("profile").getString("trade_count").replace("+","").replace(" ","")));
                    ofertaTemp.setMontoAccion(montoAccion.multiply(ofertaTemp.getPrecioBTC()));

                    if (ofertaTemp.getMontoAccion().compareTo(ofertaTemp.getMontoMin()) >= 0
                            && ofertaTemp.getMontoMax().compareTo(ofertaTemp.getMontoAccion())>=0
                            && ofertaTemp.getNombreBanco().toLowerCase().contains(bancoAccion.toLowerCase())){
                        // Nivel 1
                        if (ofertaTemp.getPerfilNroTransacciones().compareTo(new BigDecimal(50L))>=0
                                && ofertaTemp.getPerfilNroTransacciones().compareTo(new BigDecimal(500L))<=0){

                            if (ofertaTemp.getPerfilScore().compareTo(new BigDecimal(100L))>=0){
                                ofertaList.add(ofertaTemp);
                            }

                        }else if (ofertaTemp.getPerfilNroTransacciones().compareTo(new BigDecimal(501L))>=0 ){  // Nivel 2

                            if (ofertaTemp.getPerfilScore().compareTo(new BigDecimal(98L))>=0){
                                ofertaList.add(ofertaTemp);
                            }

                        }
                    }
                }

                ofertaList.sort((o1, o2) -> {
                    if (o2.getPrecioBTC().compareTo(o1.getPrecioBTC())==0){
                        return 0;
                    }else if (o2.getPrecioBTC().compareTo(o1.getPrecioBTC())>0){
                        return 1;
                    }else {
                        return -1;
                    }
                });

                if (json.getJSONObject("pagination").has("next")){
                    UrlVenta = json.getJSONObject("pagination").getString("next");
                }else{
                    break;
                }

                if (ofertaList.size()<=0){
                    System.out.println("No hay Resultados de Venta");
                }else {
                    return ofertaList.get(0);
                }
            }
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    enum TIPO_DOCUMENTO{
        FACTURA(1),
        NOTA_CREDITO(2),
        NOTA_DEBITO(3),
        NOTA_PEDIDO(4),
        NOTA_ENTREGA(5),
        NOTA_RECIBO(6),
        OTRO_DOCUMENTO(7);

        Integer tipoDocumento;

        TIPO_DOCUMENTO(Integer tipoDocumento){
            this.tipoDocumento = tipoDocumento;
        }

        public Integer getTipoDocumento() {
            return this.tipoDocumento;
        }
    }

    enum ESTADO_DOCUMENTO{
        MODIFICABLE(1),
        TERMINADO(2),
        ANULADO(3),
        ENDIAN(4);

        Integer estadoDocumento;

        ESTADO_DOCUMENTO(Integer estadoDocumento){
            this.estadoDocumento = estadoDocumento;
        }

        public Integer getEstadoDocumento() {
            return this.estadoDocumento;
        }
    }
}
