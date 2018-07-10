package com.bonusteam.favtrack.room.pojos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "multimedia_table")
public class Multimedia {
    @ColumnInfo(name = "id")
    @SerializedName("id_multimedia")
    private String id;

    @ColumnInfo(name = "name")
    @SerializedName("")
    private String name;
    private String description;
    private String idGenero;
    private String idDirector;
    private String año;
    private String multimedia_type;
    private String avatar;

}
