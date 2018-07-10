package com.bonusteam.favtrack.room.pojos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "multimedia_type_table")
public class MultimediaType {
    @ColumnInfo(name="id")
    @SerializedName("id")
    private String id;

    @ColumnInfo(name = "name")
    @SerializedName("nombre")
    private String name;

    public MultimediaType() {
    }

    public MultimediaType(String id, String name) {
        this.id = id;
        this.name = name;
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
}
