package com.bonusteam.favtrack.room.pojos;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Rodrigo Corvera on 10/7/2018.
 */

@Entity(tableName = "rutina_table")
public class Rutina {

    @PrimaryKey
    @NonNull
    private String id;

    private String titulo;

    private String descripcion;

    private String tiempo;

    private String avatar;

    private int isFavorite;

    private int isRead;

    public Rutina(@NonNull String id, String titulo, String descripcion, String tiempo, String avatar,int isFavorite,int isRead) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tiempo = tiempo;
        this.avatar = avatar;
        this.isFavorite = isFavorite;
        this.isRead = isRead;
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

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int isFavorite() {
        return isFavorite;
    }

    public void setFavorite(int favorite) {
        isFavorite = favorite;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
