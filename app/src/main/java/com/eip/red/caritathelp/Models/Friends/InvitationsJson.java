package com.eip.red.caritathelp.Models.Friends;

import java.util.List;

/**
 * Created by pierr on 01/07/2016.
 */

public class InvitationsJson {

    private int                 status;
    private String              message;
    private List<Invitation>    response;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Invitation> getResponse() {
        return response;
    }
}
