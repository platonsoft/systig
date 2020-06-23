package com.stg.systigpay.remote;

import com.stg.systigpay.servicios.UsuarioService;

public class APIUtils {
    private APIUtils(){
    };

    public static final String API_URL = "http://192.168.56.1:8096/api/";

    public static UsuarioService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UsuarioService.class);
    }
}
