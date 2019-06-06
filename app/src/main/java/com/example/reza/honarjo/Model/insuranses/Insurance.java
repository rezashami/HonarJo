package com.example.reza.honarjo.Model.insuranses;

import java.io.Serializable;
import java.util.List;

public class Insurance implements Serializable {

    private String _id;
    private String name;
    private String family;
    private List<Integer> expireDay;


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

    public List<Integer> getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(List<Integer> expireDay) {
        this.expireDay = expireDay;
    }
}
