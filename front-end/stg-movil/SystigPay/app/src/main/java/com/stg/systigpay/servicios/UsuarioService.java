package com.stg.systigpay.servicios;

import com.stg.systigpay.objetos.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {
    @GET("usuario/")
    Call<Usuario> getUsuario();

    @POST("registro/")
    Call<Usuario> addUsuario(@Body Usuario user);

    @PUT("usuario/update/{id}")
    Call<Usuario> updateUsuario(@Path("id") int id, @Body Usuario usuario);

    @DELETE("usuario/delete/{id}")
    Call<Usuario> deleteUsuario(@Path("id") int id);
}
