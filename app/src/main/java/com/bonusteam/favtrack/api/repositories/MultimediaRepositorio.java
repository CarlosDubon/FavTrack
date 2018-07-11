package com.bonusteam.favtrack.api.repositories;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.bonusteam.favtrack.api.network.AppExecutors;
import com.bonusteam.favtrack.api.network.NetworkDataSource;
import com.bonusteam.favtrack.room.dao.DietaDao;
import com.bonusteam.favtrack.room.dao.MultimediaDao;
import com.bonusteam.favtrack.room.db.FavTrackerDataBase;
import com.bonusteam.favtrack.room.pojos.Dieta;
import com.bonusteam.favtrack.room.pojos.Multimedia;

import java.util.List;

public class MultimediaRepositorio {
    private static final String LOG_TAG = DietasRepositorio.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static MultimediaRepositorio sInstance;

    private final MultimediaDao mediaDao;
    private final NetworkDataSource networkDataSource;
    private final AppExecutors executors;
    private boolean mInitialized = false;
    Context context;

    public MultimediaRepositorio(MultimediaDao mediaDao,
                                 NetworkDataSource networkDataSource,
                                 AppExecutors executors) {
        this.mediaDao = mediaDao;
        this.networkDataSource = networkDataSource;
        this.executors = executors;

        LiveData<List<Multimedia>> networkData = networkDataSource.getMediaInfo();
        networkData.observeForever(newListFromApi->{
            executors.diskIO().execute(()->{
                mediaDao.deleteAll();
                mediaDao.insertMultimedia(newListFromApi);
            });
        });
    }

    public synchronized static MultimediaRepositorio getInstance(
            MultimediaDao mediaDao,
            NetworkDataSource networkDataSource,
            AppExecutors executors) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new MultimediaRepositorio(mediaDao, networkDataSource, executors);
            }
        }
        return sInstance;
    }

    private synchronized void initializeData() {
        networkDataSource.fetchMediaInfo();
    }

    public LiveData<List<Multimedia>> getMediaInfo(){
        initializeData();
        return mediaDao.getAllMultimedia();
    }

}
