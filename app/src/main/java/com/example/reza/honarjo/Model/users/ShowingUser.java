package com.example.reza.honarjo.Model.users;

import android.arch.persistence.room.Ignore;

import java.io.Serializable;

public class ShowingUser implements Serializable {
    private Integer _id;
    private String name;
    private String family;
    @Ignore
    public ShowingUser(Integer _id, String name, String family) {
        this._id = _id;
        this.name = name;
        this.family = family;
    }

    public ShowingUser() {
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }


    @Override
    public String toString() {
        return " نام و نام خانوادگی: " + name + ' ' + family;
    }
}