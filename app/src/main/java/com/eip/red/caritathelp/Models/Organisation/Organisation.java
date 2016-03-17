package com.eip.red.caritathelp.Models.Organisation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by pierr on 24/02/2016.
 */

public class Organisation implements Serializable {

    private int     id;
    private String  name;
    private String  description;
    private String  birthday;
    private String  city;
    private String  latitude;
    private String  longitude;
//    private String  created_at;
//    private String  updated_at;

    public Organisation() {}

    public Organisation(JsonObject result) {
        id = initInt(result, "id");
        name = initStr(result, "name");
        description = initStr(result, "description");
        birthday = initStr(result, "birthday");
        city = initStr(result, "city");
        latitude = initStr(result, "latitude");
        longitude = initStr(result, "longitude");
    }

    private int initInt(JsonObject jsonObject, String memberName) {
        JsonElement jsonElement = jsonObject.get(memberName);

        if (!jsonElement.isJsonNull())
            return (jsonElement.getAsInt());
        return (-1);
    }

    private String initStr(JsonObject jsonObject, String memberName) {
        JsonElement jsonElement = jsonObject.get(memberName);

        if (!jsonElement.isJsonNull())
            return (jsonElement.getAsString());
        return (null);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
