package com.bonusteam.favtrack.api.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Define las operaciones que la clase NetworkUtils va ejecutar
 * **/
public interface DataService {

    @FormUrlEncoded
    @POST("/login")
    Call<String> login(@Field("username") String user, @Field("password")String password);

}
