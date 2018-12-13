package com.example.gaboruiz.todoapp.Class;

/**
 * Created by Gabo Ruiz on 13/12/2018.
 */

public class item {
    private Id _id;
    private String item;

    public Id get_id() {
        return _id;
    }

    public void set_id(Id _id) {
        this._id = _id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
