package com.eip.red.caritathelp.Models.Notifications;

import java.util.List;

/**
 * Created by pierr on 21/04/2016.
 */

public class JsonNotifications {

    private int                 status;
    private String              message;
    private Notifications       response;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Notifications getResponse() {
        return response;
    }
}
