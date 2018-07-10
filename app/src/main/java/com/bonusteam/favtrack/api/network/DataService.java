package com.bonusteam.favtrack.api.network;


import com.bonusteam.favtrack.room.pojos.Multimedia;

import java.util.ArrayList;

import com.bonusteam.favtrack.room.pojos.Dieta;
import com.bonusteam.favtrack.room.pojos.Rutina;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Define las operaciones que la clase NetworkUtils va ejecutar
 * **/
public interface DataService {

    @FormUrlEncoded
    @POST("/login")
    Call<String> login(@Field("username") String user, @Field("password")String password);


    @GET("/multimedia")
    Call<List<Multimedia>> getAllMediaInfo();

    @GET("/rutinas")
    Call<List<Rutina>> getRutinas();

    @GET("/diets")
    Call<List<Dieta>> getDiets();


}
