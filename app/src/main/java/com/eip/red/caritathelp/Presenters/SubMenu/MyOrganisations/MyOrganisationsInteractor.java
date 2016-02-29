package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations;

import android.content.Context;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by pierr on 24/02/2016.
 */

public class MyOrganisationsInteractor {

    private Context context;
    private User    user;
    private Network network;

    public MyOrganisationsInteractor(Context context, User user, Network network) {
        this.context = context;
        this.user = user;
        this.network = network;
    }

    public void getMyOrganisations(final IOnMyOrganisationsFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_VOLUNTEERS + user.getId() + Network.API_REQUEST_GET_MY_ORGANISATIONS)
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.get(Network.API_PARAMETER_STATUS).getAsInt() == Network.API_STATUS_ERROR)
                                listener.onConnectionInternetError(result.get(Network.API_PARAMETER_MSG).getAsString());
                            else {
                                /* FILL ORGANISATIONS MODEL IN USER MODEL */
                                user.setOrganisations(result);
                                listener.onSuccess();
                            }
                        } else
                            listener.onConnectionInternetError(error.toString());
                    }
                });
    }
}
