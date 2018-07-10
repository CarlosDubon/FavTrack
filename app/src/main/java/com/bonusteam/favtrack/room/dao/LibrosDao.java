package com.bonusteam.favtrack.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bonusteam.favtrack.room.POJOS.LibrosEntity;

import java.util.List;

@Dao
public interface LibrosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLibros(LibrosEntity libros);

    @Query("SELECT * FROM LibrosEntity")
    LiveData<List<LibrosEntity>> getAllLibros();

    @Query("SELECT * FROM LibrosEntity WHERE id =:id")
    LiveData<List<LibrosEntity>> getLibrosById(String id);
}
