package com.bonusteam.favtrack.api.network;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Permite ejecutar operaciones con la API, definidas en DataService
 * */
public class NetworkUtils {
    private static Retrofit retrofit;
    private static String BASE_URL = "http://APILINK.herokuapp.com";
    private static DataService dataService;

    public static DataService getClientInstance(Gson gson){
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        dataService = retrofit.create(DataService.class);
        return dataService;
    }
    public static DataService getClientInstance(){
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dataService = retrofit.create(DataService.class);
        return dataService;
    }
    public static DataService getClientInstanceAuth(){
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dataService = retrofit.create(DataService.class);
        return dataService;
    }
    public static DataService getClientInstanceAuth(Gson gson){
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getHeader())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        dataService = retrofit.create(DataService.class);
        return dataService;
    }



    private static OkHttpClient getHeader() {
        String token = ""; //Obtener token de usuario
        return new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(newRequest);
        }).build();
    }
}
