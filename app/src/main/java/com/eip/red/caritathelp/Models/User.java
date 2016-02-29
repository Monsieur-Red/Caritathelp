package com.eip.red.caritathelp.Models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 05/12/2015.
 */

public class User implements Serializable {

    private List<Organisation>  organisations;

    private int     id;
    private String  lastName;
    private String  firstName;
    private String  gender;
    private String  birthday;

    private String  mail;
    private String  password;
    private boolean geolocation;
    private boolean notifications;

    public User() {
        organisations = new ArrayList<>();
    }

    public User(JsonObject result) {
        organisations = new ArrayList<>();

        JsonObject  res = result.getAsJsonObject(Network.API_PARAMETER_RESPONSE);
        JsonElement mail = res.get("mail");
        JsonElement lastname = res.get("lastname");
        JsonElement firstname = res.get("firstname");
        JsonElement birthday = res.get("birthday");
        JsonElement gender = res.get("gender");
        JsonElement geolocation = res.get("allowgps");

//        System.out.println(res.toString());

        id = res.get("id").getAsInt();

        if (mail != null)
            this.mail = mail.getAsString();

        if (lastname != null)
            this.lastName = lastname.getAsString();

        if (firstname != null)
            this.firstName = firstname.getAsString();

//        if (birthday != null) {
//            this.birthday = birthday.getAsString();
//        }

        if (gender != null)
            this.gender = gender.getAsString();

        if (geolocation != null) {
            if (geolocation.getAsString().equals("true"))
                this.geolocation = true;
            else
                this.geolocation = false;
        }


    }

    public void setOrganisations(JsonObject result) {
        // Empty the list
        organisations.clear();

        // Fill the list
        for (JsonElement organisationJson : result.getAsJsonArray(Network.API_PARAMETER_RESPONSE)) {
            Organisation    organisation = new Organisation();
            String          name = organisationJson.getAsJsonObject().get("name").toString();

            organisation.setName(name.replaceAll("\"", ""));
            organisations.add(organisation);
        }

    }

    public List<Organisation> getOrganisations() {
        return organisations;
    }

    public void setOrganisations(List<Organisation> organisations) {
        this.organisations = organisations;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(List<Integer> birthdayList) {
        if (birthdayList != null) {
            int year = birthdayList.get(0);
            int month = birthdayList.get(0);
            int day = birthdayList.get(0);

            // Init Sting
            birthday = "";

            // Init Day
            if (day < 10)
                birthday += "0" + String.valueOf(day);
            else
                birthday += String.valueOf(day);

            // Init Month
            if (month < 10)
                birthday += "/0" + String.valueOf(month);
            else
                birthday += "/" + String.valueOf(month);

            // Init Year
            birthday += "/" + String.valueOf(year);
        }
        else
             birthday = null;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public String isGeolocation() {
        if (geolocation)
            return ("true");
        return ("false");
    }

    public void setGeolocation(boolean geolocation) {
        this.geolocation = geolocation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
