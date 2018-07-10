package com.bonusteam.favtrack.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.bonusteam.favtrack.room.pojos.LibrosEntity;

import java.util.List;

@Dao
public interface LibrosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLibros(LibrosEntity libros);

    @Query("SELECT * FROM LibrosEntity")
    LiveData<List<LibrosEntity>> getAllLibros();

    @Query("SELECT * FROM LibrosEntity WHERE id =:id")
    LiveData<LibrosEntity> getLibroById(String id);

    @Query("SELECT favorite FROM LibrosEntity WHERE id=:idLibro")
    LiveData<Integer> isFavorite(String idLibro);

    @Query("SELECT * FROM LibrosEntity WHERE favorite = 1")
    LiveData<List<LibrosEntity>> getFavLibros();

    @Query("UPDATE LibrosEntity SET favorite =:fav WHERE id=:idLibros")
    void updateFavoriteLibros(int fav, String idLibros);

}
