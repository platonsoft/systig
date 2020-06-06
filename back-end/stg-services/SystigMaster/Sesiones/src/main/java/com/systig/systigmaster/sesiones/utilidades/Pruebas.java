package com.systig.systigmaster.sesiones.utilidades;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.systig.base.objetos.Oferta;
import com.systig.base.repositorios.contable.entidades.Documento;
import com.systig.base.repositorios.pay.entidades.Tasa;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Pruebas {
    class pp{
        private String a;
        private String b;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();

        String strPeticion = "{\n" +
                "\t\"codigo\": { \"a\":\"T0000001\",\n \"b\":\"T0000002\"},\n" +
                "    \"descripcion\": \"TRANSFERENCIA INTERNA SYSTIG\",\n" +
                "    \"nroReferencia\": \"jesusj.alcalap@gmail.com\",\n" +
                "    \"montoRecibido\": 100000\n" +
                "}";

        Map<String, Object> peticion = mapper.readValue (strPeticion, new TypeReference<Map<String, Object>>() {});

        if (peticion.containsKey("codigo")){
            pp documento = gson.fromJson(peticion.get("codigo").toString(),pp.class);
            System.out.println("Resultado B" + documento.getB());
        }

        //peticion.get("listaItems");
    }

    /*
    String bancoCompra = "bancolombia";
        String bancoVenta = "mercantil";
        BigDecimal montoAccion = new BigDecimal(1000000L);

        Oferta oCompra = CalculoPrecioCompra(montoAccion, bancoCompra);
        BigDecimal btcComprar = montoAccion.divide(oCompra.getPrecioBTC(),8, RoundingMode.DOWN);
        Oferta oVenta = CalculoPrecioVenta(btcComprar, bancoVenta);



        BigDecimal tasaReal = oVenta.getPrecioBTC().divide(oCompra.getPrecioBTC(),8,RoundingMode.DOWN);
        BigDecimal tasaOferta = oVenta.getPrecioBTC().divide(oCompra.getPrecioBTC(),8,RoundingMode.DOWN).multiply(new BigDecimal(0.98)).setScale(2,RoundingMode.DOWN);
        BigDecimal btcVenta = oCompra.getMontoAccion().multiply(tasaOferta).setScale(0,RoundingMode.DOWN).divide(oVenta.getPrecioBTC(),8,RoundingMode.DOWN);

        System.out.println("Link Compra -> " + oCompra.getLinkAcceso());
        System.out.println("Link Venta -> " + oVenta.getLinkAcceso());
        System.out.println("Tasa Real -> " + tasaReal);
        System.out.println("Tasa Oferta -> " + tasaOferta);
        System.out.println("Monto Compra -> " + btcComprar + " BTC/" + oCompra.getMontoAccion() + " " + oCompra.getMoneda().toUpperCase());
        System.out.println("Monto Venta -> " + btcVenta + " BTC/" + oCompra.getMontoAccion().multiply(tasaOferta).setScale(0,RoundingMode.DOWN) + " " + oVenta.getMoneda().toUpperCase());
        System.out.println("Ganancia -> " + btcVenta.subtract(btcComprar) + " BTC");
    * */

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
                System.out.println("Tasa -> " + miTasa.getAvg_1h());
                listadoTasas.add(miTasa);
            }
            System.out.println( "Tasas Cargadas: " +listadoTasas.size());
            return listadoTasas;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


    public static Oferta CalculoPrecioVenta(BigDecimal montoAccion, String bancoAccion){
        String UrlVenta = "https://localbitcoins.com/sell-bitcoins-online/VES/.json";
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
                        try{
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

    public static Oferta CalculoPrecioCompra(BigDecimal montoAccion, String bancoAccion){
        String UrlCompra = "https://localbitcoins.com/buy-bitcoins-online/COP/.json";

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
                        }catch (Exception e){
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
}


/*String bancoCompra = "Bancolombia";
        String bancoVenta = "Mercantil";
        BigDecimal montoAccion = new BigDecimal(10000L);

        Oferta oCompra = CalculoPrecioCompra(montoAccion, bancoCompra);
        BigDecimal btcVenta = montoAccion.divide(oCompra.getPrecioBTC(),8, RoundingMode.DOWN);
        Oferta oVenta = CalculoPrecioVenta(btcVenta, bancoVenta);
        BigDecimal tasaReal = oVenta.getPrecioBTC().divide(oCompra.getPrecioBTC(),8,RoundingMode.DOWN);
        BigDecimal tasaOferta = oVenta.getPrecioBTC().divide(oCompra.getPrecioBTC(),8,RoundingMode.DOWN).multiply(new BigDecimal(0.98)).setScale(2,RoundingMode.DOWN);
        BigDecimal factorGanancia = (new BigDecimal(1L)).subtract((new BigDecimal(1L)).divide(tasaReal.subtract(tasaOferta),8, RoundingMode.DOWN));

        System.out.println("Link Compra -> " + oCompra.getLinkAcceso());
        System.out.println("Link Venta -> " + oVenta.getLinkAcceso());
        System.out.println("Tasa Real -> " + tasaReal);
        System.out.println("Tasa Oferta -> " + tasaOferta);
        System.out.println("Monto Compra -> " + oCompra.getMontoAccion() + " " + oCompra.getMoneda().toUpperCase());
        System.out.println("Monto Venta -> " + oCompra.getMontoAccion().multiply(tasaOferta).setScale(0,RoundingMode.DOWN) + " " + oVenta.getMoneda().toUpperCase());
        System.out.println("Ganancia -> " + factorGanancia.multiply(btcVenta) + " BTC");*/


//int numero = 0; // Numero a buscar su consecutivo
//int[] V = {3,0,0,1,0,1,3,2,0,0,0,1,2}; // Vector de muestreo

//for (int i = 0; i < V.length; i++) { // recorrido del vector
//if (V[i] == numero){ // comparar el valor centro
//if (V[i] == V[i+1]){ // verificar que el valor de la derecha sea igual al del centro
//    System.out.print(numero); // imprime el valor buscado
//}
// }
//}