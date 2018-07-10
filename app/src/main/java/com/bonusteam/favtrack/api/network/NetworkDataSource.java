package com.bonusteam.favtrack.api.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.bonusteam.favtrack.room.pojos.Dieta;
import com.bonusteam.favtrack.room.pojos.Rutina;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */

public class NetworkDataSource {

    private static final Object LOCK = new Object();
    private static NetworkDataSource sInstance;
    private final MutableLiveData<List<Dieta>> dietasDataUpdate;
    private final MutableLiveData<List<Rutina>> rutinasDataUpdate;
    private final Context mContext;
    private final AppExecutors mExecutors;

    private NetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mExecutors = executors;
        dietasDataUpdate = new MutableLiveData<List<Dieta>>();
        rutinasDataUpdate = new MutableLiveData<List<Rutina>>();
    }

    public static NetworkDataSource getInstance(Context context, AppExecutors executors) {
        Log.d("Mensaje: ", "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NetworkDataSource(context.getApplicationContext(), executors);
                Log.d("Mensaje: ", "Made new network data source");
            }
        }
        return sInstance;
    }

    public LiveData<List<Dieta>> getCurrentDiets() {
        return dietasDataUpdate;
    }

    public LiveData<List<Rutina>> getCurrentRutinas() {
        return rutinasDataUpdate;
    }


    public void fetchDiets() {
        mExecutors.networkIO().execute(()->{
            try{
                Call<List<Dieta>> dietas = NetworkUtils.getClientInstanceAuth();

                dietas.enqueue(new Callback<List<Dieta>>() {
                    @Override
                    public void onResponse(retrofit2.Call<List<Dieta>> call, Response<List<Dieta>> response) {
                        if (response.isSuccessful()) {
                            List<Dieta> allDiets = response.body();
                            dietasDataUpdate.postValue(allDiets);

                        }
                    }
                    @Override
                    public void onFailure(retrofit2.Call<List<Dieta>> call, Throwable t) {

                    }
                });

            } catch (Exception e) {

            }
        });
    }

    /**
     *
     *
     */
    public void fetchRutinas() {
        mExecutors.networkIO().execute(()->{
            try{
                //Falta ruta a la api
                Call<List<Rutina>> rutinas = NetworkUtils.getClientInstanceAuth();

                rutinas.enqueue(new Callback<List<Rutina>>() {
                    @Override
                    public void onResponse(retrofit2.Call<List<Rutina>> call, Response<List<Rutina>> response) {
                        if (response.isSuccessful()) {
                            List<Rutina> allRutinas = response.body();
                            rutinasDataUpdate.postValue(allRutinas);

                        }
                    }
                    @Override
                    public void onFailure(retrofit2.Call<List<Rutina>> call, Throwable t) {

                    }
                });

            } catch (Exception e) {

            }
        });
    }

}
