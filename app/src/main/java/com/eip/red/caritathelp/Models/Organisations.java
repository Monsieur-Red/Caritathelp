package com.eip.red.caritathelp.Models;

import java.util.ArrayList;

/**
 * Created by pierr on 11/03/2016.
 */

public class Organisations {

    public int                      status;
    public String                   message;
    public ArrayList<Organisation>  response;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Organisation> getResponse() {
        return response;
    }
}
