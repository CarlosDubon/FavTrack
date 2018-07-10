package com.bonusteam.favtrack.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bonusteam.favtrack.room.pojos.Dieta;

import java.util.List;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */
@Dao
public interface DietaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsuario(Dieta... dietas);

    @Update
    void updateUsuario(Dieta dieta);

    @Delete
    void deleteUsuario(Dieta... dietas);

    @Query("SELECT * FROM dieta_table")
    LiveData<List<Dieta>> obtenerDietas();

    @Query("SELECT * FROM dieta_table WHERE isFavorite=1")
    LiveData<List<Dieta>> obtenerDietasFavoritas();

}
