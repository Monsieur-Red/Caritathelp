package com.eip.red.caritathelp.Models.Notifications;

/**
 * Created by pierr on 21/04/2016.
 */
public class Notification {

    private int     notif_id;
    private int     id;
    private String  mail;
    private String  firstname;
    private String  lastname;

    public int getNotif_id() {
        return notif_id;
    }

    public int getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
