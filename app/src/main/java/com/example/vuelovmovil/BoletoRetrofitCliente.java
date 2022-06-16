package com.example.vuelovmovil;

import com.example.vuelovmovil.RespuestaBoleto.BoletoResponseObjectClass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BoletoRetrofitCliente {

    @POST("api/v1/boletos")
    Call<BoletoResponseObjectClass> GetPostsValue(@Body BoletoObjectClass boletoObjectClass);


}
