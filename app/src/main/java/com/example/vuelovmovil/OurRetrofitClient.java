package com.example.vuelovmovil;

import com.example.vuelovmovil.Response.MainResponseObjectClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OurRetrofitClient {

    @POST("api/v1/auth/signup/pasajero")
    Call<MainResponseObjectClass> GetPostValue(@Body MainObjectClass mainObjectClass);
}
