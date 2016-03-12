package com.eip.red.caritathelp.Models;

import java.util.ArrayList;

/**
 * Created by pierr on 11/03/2016.
 */

public class Members {

    public int                  status;
    public String               message;
    public ArrayList<Member>    response;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Member> getResponse() {
        return response;
    }
}
