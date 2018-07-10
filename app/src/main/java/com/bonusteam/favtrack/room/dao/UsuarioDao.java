package com.bonusteam.favtrack.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bonusteam.favtrack.room.pojos.Usuario;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */
@Dao
public interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsuario(Usuario... usuario);

    @Update
    void updateUsuario(Usuario usuarios);

    @Delete
    void deleteUsuario(Usuario... usuarios);

    @Query("SELECT * FROM usuario_table")
    LiveData<Usuario> obtenerUsuario();
}
