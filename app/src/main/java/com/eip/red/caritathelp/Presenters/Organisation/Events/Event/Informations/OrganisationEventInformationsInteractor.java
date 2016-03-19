package com.eip.red.caritathelp.Presenters.Organisation.Events.Event.Informations;

import android.content.Context;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Events;
import com.eip.red.caritathelp.Models.Organisation.EventsInformations;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 18/03/2016.
 */

public class OrganisationEventInformationsInteractor {

    private Context context;
    private Network network;
    private int     eventId;

    public OrganisationEventInformationsInteractor(Context context, Network network, int eventId) {
        this.context = context;
        this.network = network;
        this.eventId = eventId;
    }

    public void getEventInformations(final IOnOrganisationEventInformationsFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_ORGANISATION_EVENTS_INFORMATIONS + eventId)
                .setJsonObjectBody(json)
                .as(new TypeToken<EventsInformations>(){})
                .setCallback(new FutureCallback<EventsInformations>() {
                    @Override
                    public void onCompleted(Exception error, EventsInformations result) {
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
