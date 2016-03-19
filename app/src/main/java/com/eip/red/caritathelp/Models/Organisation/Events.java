package com.eip.red.caritathelp.Models.Organisation;

import java.util.List;

/**
 * Created by pierr on 17/03/2016.
 */

public class Events {

    public int                  status;
    public String               message;
    public List<Event>          response;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Event> getResponse() {
        return response;
    }
}
