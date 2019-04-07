package com.example.reza.honarjo.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "insurance")
public class Insurance {
    @PrimaryKey
    @NonNull
    private String _id;
    private String name;
    private String family;
    private Date expireDay;

    @NonNull
    public String get_id() {
        return _id;
    }

    public void set_id(@NonNull String _id) {
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
