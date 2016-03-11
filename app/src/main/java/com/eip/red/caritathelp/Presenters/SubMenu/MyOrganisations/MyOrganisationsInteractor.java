package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations;

import android.content.Context;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation;
import com.eip.red.caritathelp.Models.Organisations;
import com.eip.red.caritathelp.Models.User;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 24/02/2016.
 */

public class MyOrganisationsInteractor {

    private Context         context;
    private User            user;
    private Network         network;
    private Organisations   myOrganisations;

    public MyOrganisationsInteractor(Context context, User user, Network network) {
        this.context = context;
        this.user = user;
        this.network = network;
    }

    public Organisation getOrganisation(String name) {
        for (Organisation organisation : myOrganisations.getResponse()) {
            if (organisation.getName().equals(name))
                return (organisation);
        }
        return (null);
    }



    public void getMyOrganisations(final IOnMyOrganisationsFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_VOLUNTEERS + user.getId() + Network.API_REQUEST_GET_MY_ORGANISATIONS)
                .setJsonObjectBody(json)
                .as(new TypeToken<Organisations>() {
                })
                .setCallback(new FutureCallback<Organisations>() {
                    @Override
                    public void onCompleted(Exception error, Organisations result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialogError("Statut 400", result.getMessage());
                            else {
                                myOrganisations = result;
                                listener.onSuccess(getMyOrganisationsNames(result.getResponse()));
                            }
                        } else
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    private List<String>    getMyOrganisationsNames(List<Organisation> myOrganisations) {
        List<String>    myOrganisationsNames = new ArrayList<>();

        for (Organisation organisation : myOrganisations) {
            myOrganisationsNames.add(organisation.getName());
        }

        return (myOrganisationsNames);
    }


//        Ion.with(context)
//                .load("GET", Network.API_LOCATION + Network.API_REQUEST_VOLUNTEERS + user.getId() + Network.API_REQUEST_GET_MY_ORGANISATIONS)
//                .setJsonObjectBody(json)
//                .asJsonObject()
//                .setCallback(new FutureCallback<JsonObject>() {
//                    @Override
//                    public void onCompleted(Exception error, JsonObject result) {
//                        if (error == null) {
//                            // Status == 400 == error
//                            if (result.get(Network.API_PARAMETER_STATUS).getAsInt() == Network.API_STATUS_ERROR)
//                                listener.onConnectionInternetError(result.get(Network.API_PARAMETER_MSG).getAsString());
//                            else {
//                                /* FILL ORGANISATIONS MODEL IN USER MODEL */
////                                user.setOrganisations(result);
//                                listener.onSuccess();
//                            }
//                        } else
//                            listener.onConnectionInternetError(error.toString());
//                    }
//                });
//    }
}
