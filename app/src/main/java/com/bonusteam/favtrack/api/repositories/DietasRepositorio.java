package com.bonusteam.favtrack.api.repositories;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.bonusteam.favtrack.api.network.AppExecutors;
import com.bonusteam.favtrack.api.network.NetworkDataSource;
import com.bonusteam.favtrack.room.dao.DietaDao;
import com.bonusteam.favtrack.room.pojos.Dieta;

import java.util.List;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */

public class DietasRepositorio {

    private static final String LOG_TAG = DietasRepositorio.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static DietasRepositorio sInstance;
    private final DietaDao dietaDao;
    private final NetworkDataSource networkDataSource;
    private final AppExecutors appExecutors;
    private boolean mInitialized = false;
    Context context;

    private DietasRepositorio (DietaDao dietaDao, NetworkDataSource networkDataSource, AppExecutors executors, Context context) {
        this.context = context;
        //AppDatabase appDatabase = AppDatabase.getDatabaseInstance(context);
        this.dietaDao = dietaDao;
        this.networkDataSource = networkDataSource;
        this.appExecutors = executors;
        LiveData<List<Dieta>> networkData = networkDataSource.getCurrentDiets();
        networkData.observeForever(newListFromApi->{
            executors.diskIO().execute(()->{
                //appDatabase.DietaDao().insertDieta(newListFromApi);
            });
        });
    }

    public synchronized static DietasRepositorio getInstance(
            DietaDao dietaDao, NetworkDataSource networkDataSource,
            AppExecutors executors, Context context, String gameName, int i) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new DietasRepositorio(dietaDao, networkDataSource,
                        executors, context);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    private void startFetchDietService() {
        //Aqui se llamaría al servicio
        //networkDataSource.fetchDiets();

    }

    private synchronized void initializeData() {
        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialized) return;
        mInitialized = true;

        startFetchDietService();
    }

    public LiveData<List<Dieta>> getDietas(){
        initializeData();
        return dietaDao.obtenerDietas();
    }



}
