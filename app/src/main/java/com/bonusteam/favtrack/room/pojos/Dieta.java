package com.bonusteam.favtrack.room.pojos;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */
@Entity(tableName = "dieta_table")
public class Dieta {

    @PrimaryKey
    @NonNull
    private String id;

    private String titulo;

    private String descripcion;

    private String avatar;


    private int favorite;

    private int read;

    public Dieta(@NonNull String id, String titulo, String descripcion, String avatar, int favorite, int read) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.avatar = avatar;
        this.favorite = favorite;
        this.read = read;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int isFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getRead() {
        return read;
    }

}
