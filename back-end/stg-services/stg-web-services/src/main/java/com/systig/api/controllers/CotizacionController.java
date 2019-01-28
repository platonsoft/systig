package com.systig.api.controllers;

import com.systig.api.models.Preguntas;
import com.systig.api.repository.PreguntasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class CotizacionController {

    private static final String API_KEY = "";

    @Autowired
    private PreguntasRepository preguntasRepository;


    @RequestMapping(value = "/cotiza", produces = "application/json",method = RequestMethod.GET)
    public Preguntas getPregunta(@RequestParam(name = "api_key") String api_key, @RequestParam(name = "dpd",required = false) Integer dpd){
        if (validKey(api_key)>0) {
            if (dpd>0){
                return preguntasRepository.findById(dpd).get();
            }else {
                return preguntasRepository.findByBase(true);
            }

        }
        return null;
    }

    @RequestMapping(value = "/cotiza/preguntas", produces = "application/json",method = RequestMethod.GET)
    public List<Preguntas> getPreguntasList(@RequestParam(name = "api_key") String api_key){
        if (validKey(api_key)>0) {
            return preguntasRepository.findAll();
        }
        return null;
    }

    @RequestMapping(value = "/cotiza", produces = "application/json",method = RequestMethod.POST)
    public void addPresupuesto(@RequestParam(name = "api_key") String api_key, @RequestParam(name = "data",required = false) Object data){
        System.out.println("Pasamos bien");
    }

    private int validKey(String valid_key){
        if (valid_key.equals("DEMO")){
            return 2;
        }else {
            //Verifica valides del key
            return 1;
        }
    }

}
