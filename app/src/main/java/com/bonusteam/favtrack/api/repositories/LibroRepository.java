package com.bonusteam.favtrack.api.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.bonusteam.favtrack.room.dao.LibrosDao;
import com.bonusteam.favtrack.room.pojos.LibrosEntity;

import java.util.List;

public class LibroRepository {

    private LibrosDao librosDao;

    public LibroRepository(Application application){
        //AppDataBase db = AppDataBase.getInstance(application);
        //this.librosDao = db.librosDao;
    }

    public LiveData<List<LibrosEntity>> getAllLibros(){
        return librosDao.getAllLibros();
    }

    public LiveData<LibrosEntity> getLibro(String id){
        return librosDao.getLibroById(id);
    }

    public LiveData<Integer> isFavorite(String id){
        return  librosDao.isFavorite(id);
    }

    public LiveData<List<LibrosEntity>> getFavoritesLibros(){
        return librosDao.getFavLibros();
    }

    public void insertLibro(LibrosEntity libros){
        new insertLibroAsyncTask(librosDao).execute(libros);
    }

    private class insertLibroAsyncTask extends AsyncTask<LibrosEntity, Void, Void>{

        private LibrosDao librosDao;

        public insertLibroAsyncTask(LibrosDao librosDao) {
            this.librosDao = librosDao;
        }

        @Override
        protected Void doInBackground(LibrosEntity... librosEntities) {
            librosDao.insertLibros(librosEntities[0]);
            return null;
        }
    }

    public void updateFavoritesLibros(int fav, String id){
        new updateFavoritesLibrosAsyncTask(librosDao, id).execute(fav);
    }

    private class updateFavoritesLibrosAsyncTask extends AsyncTask<Integer, Void, Void>{

        private LibrosDao librosDao;
        private String id;

        public updateFavoritesLibrosAsyncTask(LibrosDao librosDao, String id) {
            this.librosDao = librosDao;
            this.id = id;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            librosDao.updateFavoriteLibros(integers[0], id);
            return null;
        }
    }

    public void deleteLibros(LibrosEntity libro){
        new deleteLibrosAsyncTask(librosDao).execute(libro);
    }

    private class deleteLibrosAsyncTask extends AsyncTask<LibrosEntity, Void, Void>{

        private LibrosDao librosDao;

        public deleteLibrosAsyncTask(LibrosDao librosDao) {
            this.librosDao = librosDao;
        }


        @Override
        protected Void doInBackground(LibrosEntity... librosEntities) {
            librosDao.deleteLibros(librosEntities[0]);
            return null;
        }
    }
}
