package com.eip.red.caritathelp.Models.Organisation;

import java.util.List;

/**
 * Created by pierr on 18/03/2016.
 */

public class EventsInformations {

    public int                      status;
    public String                   message;
    public EventInformations        response;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public EventInformations getResponse() {
        return response;
    }
}
