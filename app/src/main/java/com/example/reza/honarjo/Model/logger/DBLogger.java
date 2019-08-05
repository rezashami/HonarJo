package com.example.reza.honarjo.Model.logger;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.reza.honarjo.Controller.db.DateConverter;
import com.example.reza.honarjo.Controller.db.UserListConverter;

import java.io.Serializable;
import java.util.Date;
@Entity(tableName = "report")
@TypeConverters({DateConverter.class, UserListConverter.class})
public class DBLogger implements Serializable{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private Date date;
    private String header;
    private String body;

    public DBLogger(){
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
