package com.eip.red.caritathelp.Models.Home;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by pierr on 11/01/2016.
 */

public class News implements Serializable {

//    private Bitmap  logo;
    private int     logo;
    private String  title;
    private String  date;
    private String  description;

    public News(int logo, String title, String date, String description) {
        this.logo = logo;
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public int getLogo() {
        return logo;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
