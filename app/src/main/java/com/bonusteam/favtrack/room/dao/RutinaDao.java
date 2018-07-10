package com.bonusteam.favtrack.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bonusteam.favtrack.room.pojos.Dieta;
import com.bonusteam.favtrack.room.pojos.Rutina;

import java.util.List;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */
@Dao
public interface RutinaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRutina(List<Rutina> rutinas);

    @Update
    void updateRutina(Rutina rutina);

    @Delete
    void deleteRutina(Rutina... rutinas);

    @Query("SELECT * FROM rutina_table")
    LiveData<List<Rutina>> obtenerRutinas();

    @Query("SELECT * FROM rutina_table WHERE isFavorite=1")
    LiveData<List<Rutina>> obtenerRutinasFavoritas();
}
