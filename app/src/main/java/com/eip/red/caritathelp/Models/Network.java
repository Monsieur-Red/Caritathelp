package com.eip.red.caritathelp.Models;

import android.content.Context;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pierr on 05/12/2015.
 */

public class Network implements Serializable {

    static final public  String     API_LOCATION = "http://52.31.151.160:3000/";
    static final public  String     API_REQUEST_LOGIN = "login";
    static final public  String     API_REQUEST_ORGANISATION = "associations";
    static final public  String     API_REQUEST_VOLUNTEERS = "volunteers/";

    static final public  String     API_REQUEST_GET_MY_ORGANISATIONS = "/associations";

    static final public  String     API_REQUEST_SUBSCRIBE = "volunteers";
    static final public  String     API_REQUEST_ACCOUNT_SETTINGS_MODIFICATION = "volunteers/";
    static final public  String     API_REQUEST_VOLUNTEER_DELETE_ACCOUNT = "volunteers/";

    /* API PARAMETERS */
    static final public  String     API_PARAMETER_STATUS = "status";
    static final public  String     API_PARAMETER_MSG = "message";
    static final public  String     API_PARAMETER_RESPONSE = "response";

    /* API STATUS */
    static final public int     API_STATUS_ERROR = 400;
    static final public int     API_STATUS_SUCCESS = 200;

    /* API MSG */
    /* LOGIN */
    static final public  String     API_MSG_OK = "ok";
    static final public  String     API_MSG_UNKNOWN_MAIL = "Unknown mail";
    static final public  String     API_MSG_WRONG_PASSWORD = "Wrong password";
    /* SUBSCRIBE */
    static final public  String     API_MSG_INVALID_MAIL = "Validation failed: Mail is invalid";
    static final public  String     API_MSG_UNAVAILABLE_MAIL = "Unavailable mail";

//    private Context context;
    private String  token;

    public Network() {

    }

    public Network(JsonObject result) {
        token = result.getAsJsonObject(Network.API_PARAMETER_RESPONSE).get("token").getAsString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //    public void getJson() {
//        Ion.with(context)
//                .load("http://example.com/thing.json")
//                .asJsonObject()
//                .setCallback(new FutureCallback<JsonObject>() {
//                    @Override
//                    public void onCompleted(Exception e, JsonObject result) {
//                        // do stuff with the result or error
//                    }
//                });
//    }
//
//    public void postJson(String request, List<String> paramNames, List<String> values) {
//        JsonObject json = new JsonObject();
//        for (int i = 0; i < paramNames.size(); i++) {
//            json.addProperty(paramNames.get(i), values.get(i));
//        }
//
//        Ion.with(context)
//                .load(API_LOCATION + request)
//                .setJsonObjectBody(json)
//                .asJsonObject()
//                .setCallback(new FutureCallback<JsonObject>() {
//                    @Override
//                    public void onCompleted(Exception e, JsonObject result) {
//                        // do stuff with the result or error
//                        System.out.println("RESULT : " + result.toString());
//
//                    }
//                });
//    }
}
