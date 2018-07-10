package com.bonusteam.favtrack.api.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.bonusteam.favtrack.room.dao.UsuarioDao;
import com.bonusteam.favtrack.room.db.FavTrackerDataBase;
import com.bonusteam.favtrack.room.pojos.Usuario;

import java.util.List;

public class UserRepository {

    private UsuarioDao usuarioDao;

    public UserRepository(Application application) {
        FavTrackerDataBase db = FavTrackerDataBase.getDatabase(application);
        this.usuarioDao = db.usuarioDao();
    }

    public LiveData<Usuario> getUser() {
        return usuarioDao.obtenerUsuario();
    }

    public void updateUser(Usuario usuario) {
        new updateUserAsyncTask(usuarioDao).execute(usuario);
    }

    private class updateUserAsyncTask extends AsyncTask<Usuario, Void, Void> {

        private UsuarioDao usuarioDao;

        public updateUserAsyncTask(UsuarioDao usuarioDao) {
            this.usuarioDao = usuarioDao;
        }

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            usuarioDao.updateUsuario(usuarios[0]);
            return null;
        }
    }

    public void insertUser(Usuario... usuarios) {
        new insertUserAsyncTask(usuarioDao).execute(usuarios);
    }

    private class insertUserAsyncTask extends AsyncTask<Usuario, Void, Void> {

        private UsuarioDao usuarioDao;

        public insertUserAsyncTask(UsuarioDao usuarioDao) {

            this.usuarioDao = usuarioDao;
        }

        @Override
        protected Void doInBackground(Usuario... usuarios) {
            usuarioDao.insertUsuario(usuarios);
            return null;
        }
    }

    public void deleteUser(Usuario... usuarios) {
        new deleteUserAsyncTask(usuarioDao).execute(usuarios);
    }

    private class deleteUserAsyncTask extends AsyncTask<Usuario, Void, Void> {

        private UsuarioDao usuarioDao;

        public deleteUserAsyncTask(UsuarioDao usuarioDao) {
            this.usuarioDao = usuarioDao;
        }


        @Override
        protected Void doInBackground(Usuario... usuarios) {
            usuarioDao.deleteUsuario(usuarios);
            return null;
        }
    }
}
