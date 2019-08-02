package com.example.reza.honarjo.Model.queryResults;

import java.io.Serializable;
import java.util.Date;

public class ExpireNameFamilyID implements Serializable {
    private Integer _id;
    private String name;
    private String family;
    private Date expireDay;

    public ExpireNameFamilyID() {
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

    public Date getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Date expireDay) {
        this.expireDay = expireDay;
    }
}
