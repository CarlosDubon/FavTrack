package com.bonusteam.favtrack.api.repositories;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.bonusteam.favtrack.api.network.AppExecutors;
import com.bonusteam.favtrack.api.network.NetworkDataSource;
import com.bonusteam.favtrack.room.dao.RutinaDao;
import com.bonusteam.favtrack.room.pojos.Rutina;

import java.util.List;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */

public class RutinasRepositorio {



    private static final Object LOCK = new Object();
    private static RutinasRepositorio sInstance;
    private final RutinaDao rutinaDao;
    private final NetworkDataSource networkDataSource;
    private final AppExecutors appExecutors;
    private boolean mInitialized = false;
    Context context;

    private RutinasRepositorio (RutinaDao rutinaDao, NetworkDataSource networkDataSource, AppExecutors executors, Context context) {
        this.context = context;
        //AppDatabase appDatabase = AppDatabase.getDatabaseInstance(context);
        this.rutinaDao = rutinaDao;
        this.networkDataSource = networkDataSource;
        this.appExecutors = executors;
        LiveData<List<Rutina>> networkData = networkDataSource.getCurrentRutinas();
        networkData.observeForever(newListFromApi->{
            executors.diskIO().execute(()->{
                appDatabase.RutinaDao().insertRutina(newListFromApi);
            });
        });
    }

    public synchronized static RutinasRepositorio getInstance(
            RutinaDao rutinaDao, NetworkDataSource networkDataSource,
            AppExecutors executors, Context context, String gameName, int i) {
        //Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RutinasRepositorio(rutinaDao, networkDataSource,
                        executors, context);
                //Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    private void startFetchRutinaService() {
        //Aqui se llamaría al servicio
        networkDataSource.fetchRutinas();
    }

    private synchronized void initializeData() {
        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialized) return;
        mInitialized = true;

        startFetchRutinaService();
    }

    public LiveData<List<Rutina>> getRutinas(){
        initializeData();
        return rutinaDao.obtenerRutinas();
    }



}
