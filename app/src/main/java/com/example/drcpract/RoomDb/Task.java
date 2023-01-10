package com.example.drcpract.RoomDb;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "vicinity")
    public String vicinity;

    @ColumnInfo(name = "lat")
    public String lat;

    @ColumnInfo(name = "long")
    public String lng;


    /*
    * Getters and Setters
    * */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return vicinity;
    }

    public void setSurname(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getBirthday() {
        return lat;
    }

    public void setBirthday(String lat) {
        this.lat = lat;
    }

    public String getPhoto() {
        return lng;
    }

    public void setPhoto(String photo) {
        this.lng = lng;
    }
}
