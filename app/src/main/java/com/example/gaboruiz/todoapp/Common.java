package com.example.gaboruiz.todoapp;

import com.example.gaboruiz.todoapp.Class.item;

/**
 * Created by Gabo Ruiz on 13/12/2018.
 */

public class Common {
    private static String DB_NAME = "todo-app";
    private static String COLLECTION_NAME = "todo_list_list";
    public static String API_KEY = "e1f-OPYfaj7t_c62xy-gkTXncIhTBttd";

    public static String getAddressSingle(item item){
        String baseURL = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder(baseURL);
        stringBuilder.append("/"+item.get_id().getOid()+"?apiKey="+API_KEY);
        return stringBuilder.toString();
    }

    public static String getAddressAPI(){
        String baseURL = String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",DB_NAME,COLLECTION_NAME);
        StringBuilder stringBuilder = new StringBuilder(baseURL);
        stringBuilder.append("?apiKey="+API_KEY);
        return stringBuilder.toString();
    }
}


