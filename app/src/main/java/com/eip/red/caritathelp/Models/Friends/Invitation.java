package com.eip.red.caritathelp.Models.Friends;

/**
 * Created by pierr on 01/07/2016.
 */

public class Invitation {

    private int     id;
    private String  firstname;
    private String  lastname;
    private String  thumb_path;
    private int     notif_id;

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getThumb_path() {
        return thumb_path;
    }

    public int getNotif_id() {
        return notif_id;
    }
}
