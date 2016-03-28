package com.eip.red.caritathelp.Models;

import com.eip.red.caritathelp.Models.Organisation.Organisation;

import java.util.ArrayList;

/**
 * Created by pierr on 28/03/2016.
 */

public class OrganisationJson {

    public int                      status;
    public String                   message;
    public Organisation             response;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Organisation getResponse() {
        return response;
    }
}
