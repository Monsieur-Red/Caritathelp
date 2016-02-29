package com.eip.red.caritathelp.Presenters.Login;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Views.Login.LoginView;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 09/11/2015.
 */

public class LoginInteractor {

    static final private String     ERROR_MANDATORY = "Ce champ est obligatoire";

    private Context context;

    public LoginInteractor(Context context) {
        this.context = context;
    }

    public void login(final String email, final String password, final IOnLoginFinishedListener listener) {
        // Mock login. I'm creating a handler to delay the answer a couple of seconds
//        new Handler().postDelayed(new Runnable() {
//            @Override public void run() {
//
                if (!simpleVerification(email, password, listener))
                    apiRequest(email, password, listener);
//
//            }
//        }, 1000);
    }

    private boolean simpleVerification(final String email, final String password, final IOnLoginFinishedListener listener) {
        boolean error = false;

        if (TextUtils.isEmpty(email)){
            listener.onEmailError(ERROR_MANDATORY);
            error = true;
        }
        if (TextUtils.isEmpty(password)){
            listener.onPasswordError(ERROR_MANDATORY);
            error = true;
        }
        return (error);
    }

    private void apiRequest(String email, String password, final IOnLoginFinishedListener listener) {
        JsonObject  json = new JsonObject();

        json.addProperty("mail", email);
        json.addProperty("password", password);

        Ion.with(context)
                .load(Network.API_LOCATION + Network.API_REQUEST_LOGIN)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {

                        if (error != null) {
                            // RIGHT METHOD!
                            listener.onConnectionInternetError(error.toString());

                            // METHOD WITHOUT INTERNET CONNECTION
//                            listener.onSuccess(new User(), new Network());
                        }
                        else {
                            if (result.get(Network.API_PARAMETER_STATUS).getAsInt() == Network.API_STATUS_ERROR) {
                                if (result.get(Network.API_PARAMETER_MSG).getAsString().equals(Network.API_MSG_UNKNOWN_MAIL))
                                    listener.onEmailError("Mail inconnu");
                                else
                                    listener.onPasswordError("Mauvais mot de passe");
                            } else {
                                // Init User & Token Model
                                listener.onSuccess(new User(result), new Network(result));
                            }
                        }
                    }
                });
    }

}

/* API RESULT */
//RESULT : {"status":400,"message":"Unknown mail","response":null}
//RESULT : {"status":400,"message":"Wrong password","response":null}
//RESULT : {"status":200,"message":"ok","response":{"token":"kotP104XQVS-nveMxYIQGQ"}}