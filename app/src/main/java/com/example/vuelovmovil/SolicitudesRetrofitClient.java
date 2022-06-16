package com.example.vuelovmovil;

import com.example.vuelovmovil.Respuesta.SolicitudesResponseObjectClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SolicitudesRetrofitClient {

    @POST("/api/v1/contactanos")
    Call<SolicitudesResponseObjectClass> GetPostValued(@Body SolicitudesObjectClass solicitudesObjectClass);
}
