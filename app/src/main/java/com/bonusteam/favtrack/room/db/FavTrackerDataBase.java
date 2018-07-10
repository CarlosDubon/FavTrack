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
import com.bonusteam.favtrack.room.pojos.LibrosEntity;
import com.bonusteam.favtrack.room.pojos.Multimedia;
import com.bonusteam.favtrack.room.pojos.Rutina;
import com.bonusteam.favtrack.room.pojos.Usuario;

@Database(entities = {Dieta.class, LibrosDao.class, Multimedia.class, Rutina.class, Usuario.class},version = 1)
public abstract class FavTrackerDataBase extends RoomDatabase{
    public abstract DietaDao dietaDao();
    public abstract LibrosDao librosDao();
    public abstract MultimediaDao multimediaDao();
    public abstract RutinaDao rutinaDao();
    public abstract UsuarioDao usuarioDao();

    private static FavTrackerDataBase INSTANCE;

    public static FavTrackerDataBase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (FavTrackerDataBase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),FavTrackerDataBase.class,"fav_tracker_db")
                            .addCallback(addMultimediaCallBack)
                            .addCallback(addDefautltUserCallback)
                            .addCallback(addDefautltLibrosCallback)
                            .addCallback(addDefautltRutinasCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback addDefautltDietaCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new addDietaDefault(INSTANCE).execute();
        }
    };

    private static RoomDatabase.Callback addDefautltRutinasCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new addRutinaDefault(INSTANCE).execute();
        }
    };

    private static RoomDatabase.Callback addDefautltLibrosCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new addLibrosDefault(INSTANCE).execute();
        }
    };

    private static RoomDatabase.Callback addDefautltUserCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new addDefaultUser(INSTANCE).execute();
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
    private static class addLibrosDefault extends AsyncTask<Void,Void,Void>{
        private LibrosDao librosDao;

        public addLibrosDefault(FavTrackerDataBase db){
            librosDao = db.librosDao();
        }

        /**
         * Meotodo para agregar informacion predeterminada
         * @param params
         * @return
         */
        @Override
        protected Void doInBackground(Void... params) {
            LibrosEntity librosEntity = new LibrosEntity("12","El código da vinci","The Da Vinci Code is a mystery novel written by Dan Brown. [...] By combining the genres of detective suspense and New Age esotericism, with a conspiracy theory concerning the Holy Grail and the role of Mary Magdalene in Christianity, the novel spurred the widespread interest (especially in the United States of America)", "Dan Brown","Narrativa", "Planeta","2003", "https://www.planetadelibros.com/usuaris/libros/fotos/254/m_libros/portada_el-codigo-da-vinci_dan-brown_201706061701.jpg",0);

            LibrosEntity librosEntity1 = new LibrosEntity("13", "The fault in our stars", "Two teenage cancer patients begin a journey to reaffirm their lives and visit a lone writer in Amsterdam.",  "John Green","Romance", "E.P. Dutton", "2012", "https://upload.wikimedia.org/wikipedia/en/thumb/a/a9/The_Fault_in_Our_Stars.jpg/200px-The_Fault_in_Our_Stars.jpg",0);

            LibrosEntity librosEntity2 = new LibrosEntity("14", "El  diario de Ana Frank", "With the title of The Diary of Anne Frank is known the edition of the diaries written by the Jewish girl Anne Frank (Annelies Marie Frank) between June 12, 1942 and August 1, 1944 in a total of three notebooks preserved the present, where he tells his story as a teenager and the time of two years when he had a concealment...",
            "Ana Frank","Clasico", "Person","2008","https://imagessl6.casadellibro.com/a/l/t0/46/9788449331046.jpg",0);

            librosDao.insertLibros(librosEntity);
            librosDao.insertLibros(librosEntity1);
            librosDao.insertLibros(librosEntity2);

            return null;
        }
    }
    private static class addRutinaDefault extends AsyncTask<Void,Void,Void>{
        private RutinaDao rutinaDao;

        public addRutinaDefault(FavTrackerDataBase db){
            rutinaDao = db.rutinaDao();
        }

        /**
         * Meotodo para agregar informacion predeterminada
         * @param params
         * @return
         */
        @Override
        protected Void doInBackground(Void... params) {
            Rutina rutina = new Rutina("15","Rutina de Brazos","Con esta rutina, se podra ejercitar diversos músculos del brazo de una manera equilibrada. Realizar al menos dos veces a la semana.","15 minutos", "https://www.totalfitness.es/blog/wp-content/uploads/DIA-01.jpg",0,0);
            Rutina rutina1 = new Rutina ("16","Rutina de Piernas","Esta rutina consiste en ejercitar las piernas de manera balanceada, para tonificar los musculos del area en cuestion. Se recomienda realizar esta rutina al menos dos veces a la semana. En conjunto con la rutina de brazos.", "10 minutos", "https://entrenar.me/blog/wp-content/uploads/2018/02/rutina-de-piernas-para-gym-lunge-bueno.jpg",0,0);
            rutinaDao.insertRutina(rutina);
            rutinaDao.insertRutina(rutina1);
            return null;
        }
    }

    private static class addDietaDefault extends AsyncTask<Void,Void,Void>{
        private DietaDao dietaDao;

        public addDietaDefault(FavTrackerDataBase db){
            dietaDao = db.dietaDao();
        }

        /**
         * Meotodo para agregar informacion predeterminada
         * @param params
         * @return
         */
        @Override
        protected Void doInBackground(Void... params) {
            Dieta dieta = new Dieta("20", "Dieta Mediterranea", "La dieta mediterránea original se basada en un mayor consumo de vegetales y grasas monoinsaturadas (los cultivos tradicionales de los países mediterráneos) y en un menor consumo de carnes, acompañados de un estilo de vida muy activo.", "https://quierocuidarme.dkvsalud.es/sites/default/files/styles/vivelasalud_ficha_825x464/public/imagen/2018-04/dieta-mediterranea_0.jpg?itok=06zZ7WVT",0,0);
            Dieta dieta1 = new Dieta("21", "Dieta DASH"," Básicamente se trata de una dieta basada en el consumo de legumbres, frutas y verduras, cereales siempre integrales, lácteos desnatados y carnes magras provenientes de pescados y de aves. Según los expertos, se trata de la dieta más saludable y efectiva a la hora de perder peso.", "https://www.medicacontrol.com/wp-content/uploads/2016/05/DIETA-HIPERTENSION.jpg",0,0);
            dietaDao.deleteDieta(dieta);
            dietaDao.deleteDieta(dieta1);

            return null;
        }
    }


}
