package com.eip.red.caritathelp.Presenters.Organisation.Events;

import android.content.Context;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Events;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 18/03/2016.
 */

public class OrganisationEventsInteractor {

    private Context context;
    private Network network;
    private int     organisationId;

    public OrganisationEventsInteractor(Context context, Network network, int organisationId) {
        this.context = context;
        this.network = network;
        this.organisationId = organisationId;
    }

    public void getEvents(final IOnOrganisationEventsFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_ORGANISATION + "/" + organisationId + Network.API_REQUEST_ORGANISATION_EVENTS)
                .setJsonObjectBody(json)
                .as(new TypeToken<Events>(){})
                .setCallback(new FutureCallback<Events>() {
                    @Override
                    public void onCompleted(Exception error, Events result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialogError("Statut 400", result.getMessage());
                            else
                                listener.onSuccess(result.getResponse());
                        }
                        else
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

}
