package com.bonusteam.favtrack.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bonusteam.favtrack.room.pojos.Multimedia;

import java.util.List;

@Dao
public interface MultimediaDao {

    /**
     * Insersion de multimedia
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMultimedia();

    /**
     * Obtencion de todos los registros pertenecientes a multimedia
     * @return lista de objeto multimedia
     */
    @Query("SELECT * FROM multimedia_table")
    LiveData<List<Multimedia>> getAllMultimedia();

    /**
     * Obtencion de todos los registros favoritos
     * @return lista de favoritos
     */
    @Query("SELECT * FROM multimedia_table WHERE favorite == 1")
    LiveData<List<Multimedia>> getMultimediaFavotites();

    /**
     * Obtencion de los registros marcados como leidos
     * @return lista de multimedia leida
     */
    @Query("SELECT * FROM multimedia_table WHERE read == 1")
    LiveData<List<Multimedia>> getMultimediaRead();
}
