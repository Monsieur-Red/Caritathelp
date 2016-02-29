package com.eip.red.caritathelp.Models;

/**
 * Created by pierr on 24/02/2016.
 */

public class Organisation {

    private int     id;
    private String  name;
    private String  description;
    private String  birthday;
    private String  city;
    private String  latitude;
    private String  longitude;
    private String  created_at;
    private String  updated_at;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
