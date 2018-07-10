package com.bonusteam.favtrack.room.db;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.bonusteam.favtrack.room.dao.DietaDao;
import com.bonusteam.favtrack.room.dao.LibrosDao;
import com.bonusteam.favtrack.room.dao.MultimediaDao;
import com.bonusteam.favtrack.room.dao.RutinaDao;
import com.bonusteam.favtrack.room.dao.UsuarioDao;
import com.bonusteam.favtrack.room.pojos.Dieta;
import com.bonusteam.favtrack.room.pojos.Multimedia;
import com.bonusteam.favtrack.room.pojos.Rutina;
import com.bonusteam.favtrack.room.pojos.Usuario;

@Database(entities = {Dieta.class, LibrosDao.class, Multimedia.class, Rutina.class, Usuario.class},version = 1)
public abstract class FavTrackerDataBase extends RoomDatabase{
    public abstract DietaDao dietaDao();
    public abstract LibrosDao librosDao();
    public abstract MultimediaDao multimediaDao();
    public abstract RutinaDao rutinaDaoDao();
    public abstract UsuarioDao usuarioDao();

    private static FavTrackerDataBase INSTANCE;

    public static FavTrackerDataBase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (FavTrackerDataBase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),FavTrackerDataBase.class,"fav_tracker_db")
                        .addCallback(addMultimediaCallBack)
                        .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback addDefautltUser = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            //new addMultimediaDefault(INSTANCE).execute();
        }
    };

    private static RoomDatabase.Callback addMultimediaCallBack = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new addMultimediaDefault(INSTANCE).execute();
        }
    };

    private static class addDefaultUser extends AsyncTask<Void,Void,Void>{
        private UsuarioDao usuarioDao;
        public addDefaultUser(FavTrackerDataBase db){
            usuarioDao = db.usuarioDao();
        }
        @Override
        protected Void doInBackground(Void... params) {
            Usuario usuario = new Usuario("78", "Admin", "root", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmgT3-nqyMvbD4-JVpjIQZuYx18AfMSZA4CDmJUsA67ddKe1lmcA");
            usuarioDao.insertUsuario(usuario);
            return null;
        }
    }
    private static class addMultimediaDefault extends AsyncTask<Void,Void,Void>{
        private MultimediaDao multimediaDao;

        public addMultimediaDefault(FavTrackerDataBase db){
            multimediaDao = db.multimediaDao();
        }

        /**
         * Meotodo para agregar informacion predeterminada
         * @param params
         * @return
         */
        @Override
        protected Void doInBackground(Void... params) {
            Multimedia single = new Multimedia("1", "Avengers Infinity War" ,
                            "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and            existence itself has never been more uncertain.",
                    "Ciencia Ficcion", "Anthony Russo",
                        "2018" , "Pelicula", "https://www.metro.us/sites/default/files/styles/normal_article/public/main/articles/avengers-infinity-war-2018-banner.jpg", 0,0);

            Multimedia single2 = new Multimedia("2", "Moana", "In Ancient Polynesia, when a terrible curse incurred by Maui reaches an impetuous Chieftain's daughter's island, she answers the Ocean's call to seek out the demigod to set things right.", "Familiar"," Stephen Chbosky",
            "2016", "Pelicula", "https://image.tmdb.org/t/p/original/gyDpttDrIS5fJkzQ5u4sSLGqSgX.jpg", 0,0);

            Multimedia single3 = new Multimedia("3" ,"Wonder", "The story of August Pullman – a boy with facial differences – who enters fifth grade,attending a mainstream elementary school for the first time.", "Drama", " Ron Clements", "2017", "Pelicula", "https://gestionandohijos.com/wp-content/uploads/2017/12/wonder-film.jpg", 0,0);

            multimediaDao.insertMultimedia(single);
            multimediaDao.insertMultimedia(single2);
            multimediaDao.insertMultimedia(single3);
            return null;
        }
    }

}
