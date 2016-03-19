package com.eip.red.caritathelp.Models.Organisation;

/**
 * Created by pierr on 18/03/2016.
 */

public class EventInformations {

    private int         id;
    private String      title;
    private String      description;
    private String      place;
    private String      begin;
    private String      end;
    private int         assoc_id;
    private String      rights;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }

    public int getAssoc_id() {
        return assoc_id;
    }

    public String getRights() {
        return rights;
    }
}
