package com.bonusteam.favtrack.room.pojos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class LibrosEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String idLibro;

    private String nombre;

    private String descripcion;

    private String genero;

    private String autor;

    private String editorial;

    private String anio;

    private String avatar;

    private int favorite = 0;

    public LibrosEntity(@NonNull String idLibro, String nombre, String descripcion, String genero, String autor, String editorial, String anio, String avatar, int favorite) {
        this.idLibro = idLibro;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.genero = genero;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
        this.avatar = avatar;
        this.favorite = favorite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(@NonNull String idLibro) {
        this.idLibro = idLibro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
}
