package com.example.reza.honarjo.Model.users;

public class UpdateUser {
    private User user;
    private String _id;

    public UpdateUser(User user, String _id) {
        this.user = user;
        this._id = _id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
