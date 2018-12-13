package com.example.gaboruiz.todoapp.Class;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gabo Ruiz on 13/12/2018.
 */

public class Id {
    @SerializedName("$oid")
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
