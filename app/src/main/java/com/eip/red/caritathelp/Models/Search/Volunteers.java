package com.eip.red.caritathelp.Models.Search;

import com.eip.red.caritathelp.Models.Organisation.Event;

import java.util.List;

/**
 * Created by pierr on 21/04/2016.
 */

public class Volunteers {

    public int              status;
    public String           message;
    public List<Volunteer>  response;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Volunteer> getResponse() {
        return response;
    }
}
