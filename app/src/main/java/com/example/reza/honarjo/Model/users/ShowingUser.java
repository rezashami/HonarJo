package com.example.reza.honarjo.Model.users;

import java.io.Serializable;

public class ShowingUser implements Serializable {
    private String _id;
    private String name;
    private String family;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
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
}
