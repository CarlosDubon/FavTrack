package com.bonusteam.favtrack.room.pojos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.SerializedName;

/**
 * Entidad Multimedia
 */
@Entity(tableName = "multimedia_table")
public class Multimedia {
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private String id;

    @ColumnInfo(name = "name")
    @SerializedName("nombre")
    private String name;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;

    @SerializedName("id_genero")
    @ColumnInfo(name = "id_genero")
    private String idGenero;

    @SerializedName("id_director")
    @ColumnInfo(name="id_director")
    private String idDirector;

    @SerializedName("anio")
    @ColumnInfo(name = "anio")
    private String anio;

    @SerializedName("id_multimedia_type")
    @ColumnInfo(name = "id_multimedia_type")
    private String id_multimedia_type;

    @SerializedName("avatar")
    @ColumnInfo(name = "avatar")
    private String avatar;

    private int isFavorite = 0;

    private int isRead = 0;

    public Multimedia() {
    }

    public Multimedia(String id, String name, String description, String idGenero, String idDirector, String anio, String id_multimedia_type, String avatar, int isFavorite, int isRead) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idGenero = idGenero;
        this.idDirector = idDirector;
        this.anio = anio;
        this.id_multimedia_type = id_multimedia_type;
        this.avatar = avatar;
        this.isFavorite = isFavorite;
        this.isRead = isRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(String idGenero) {
        this.idGenero = idGenero;
    }

    public String getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(String idDirector) {
        this.idDirector = idDirector;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getId_multimedia_type() {
        return id_multimedia_type;
    }

    public void setId_multimedia_type(String id_multimedia_type) {
        this.id_multimedia_type = id_multimedia_type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
